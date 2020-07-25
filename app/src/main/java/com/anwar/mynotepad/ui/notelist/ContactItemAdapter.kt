package com.anwar.mynotepad.ui.notelist

import android.app.AlertDialog
import android.app.Application
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.anwar.mynotepad.R
import com.anwar.mynotepad.datarepository.database.NoteContactDatabase
import com.anwar.mynotepad.databinding.CreateNewItemBinding
import com.anwar.mynotepad.databinding.ItemListBinding
import com.anwar.mynotepad.datarepository.model.Contactdata


class ContactItemAdapter(val clickListener: ContactItemListener):
    ListAdapter<Contactdata, ContactItemAdapter.ViewHolder>(
        ContactListDiffUtils()
    ){
    /**
     * Creates and returns ViewHolder on request from the RecyclerView
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(
            parent
        )
    }

    /**
     * This function takes a ViewHolder, gets a shopping item at a position and binds the
     *data to the ViewHolder
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position),clickListener)
    }

    class ViewHolder private constructor(private val binding: ItemListBinding):
        RecyclerView.ViewHolder(binding.root){
        val dataSource = NoteContactDatabase.getInstance(itemView.context).noteContactDao
        val viewModel =
            ContactListViewModel(
                dataSource,
                Application()
            )

        /**
         * This function takes in a shopping item and uses DataBinding to attach each view in the item_list.xmlxml layout to data
         * from the shopping item.
         */
        fun bind(item: Contactdata, clickListener: ContactItemListener){
            binding.item = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
            binding.deleteBtn.setOnClickListener {
                deleteItem(item)
            }
            binding.editBtn.setOnClickListener {view ->
                editItem(view,item)
            }
        }

        /**
         * This function is called when the edit button is pressed.
         * It takes in a [view] and the corresponding [item] in that view which is being updated.
         * @param view the view in scope
         * @param item the item to be edited and saved to the database.
         */
        private fun editItem(view: View, item: Contactdata) {
            val builder = AlertDialog.Builder(view.context)
            val layoutInflater = LayoutInflater.from(view.context)
            val alertDialog: AlertDialog
            val layoutView = layoutInflater.inflate(R.layout.create_new_item,null)
            val newItemBinding = CreateNewItemBinding.bind(layoutView)
            newItemBinding.etNoteName.setText(item.noteName)
            newItemBinding.etNoteDescription.setText(item.noteDescription.toString())
            newItemBinding.etNoteNumber.setText(item.noteNumber.toString())
            builder.setView(layoutView)
            alertDialog = builder.create()
            alertDialog.show()

            newItemBinding.btnSave.setOnClickListener {

                binding.item = item.apply {
                    noteName = newItemBinding.etNoteName.text.toString()
                    noteDescription = newItemBinding.etNoteDescription.text.toString()
                    noteNumber = newItemBinding.etNoteNumber.text.toString().toInt()
                }

                viewModel.onEditButtonClicked(item)
                alertDialog.dismiss()
            }
        }

        /**
         * This function is called when the delete button is pressed.
         * It takes in an [item] and deletes the item from the database with the help of the viewmodel.
         * @param item the item to be deleted from the database
         */
        private fun deleteItem(item: Contactdata) {
            item.let {
               viewModel.onDeleteButtonPressed(item)
            }
        }

        companion object{
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemListBinding.inflate(layoutInflater,parent,false)
                return ViewHolder(
                    binding
                )
            }
        }

    }
}

class ContactListDiffUtils: DiffUtil.ItemCallback<Contactdata>(){
    override fun areItemsTheSame(oldItem: Contactdata, newItem: Contactdata): Boolean {
        return oldItem.noteId == newItem.noteId
    }

    override fun areContentsTheSame(oldItem: Contactdata, newItem: Contactdata): Boolean {
        return oldItem == newItem
    }

}

class ContactItemListener(val clickListener: (itemId: Long) -> Unit){
    fun onClick(item: Contactdata) = clickListener(item.noteId)
}