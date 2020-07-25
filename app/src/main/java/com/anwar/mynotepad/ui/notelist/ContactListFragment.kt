package com.anwar.mynotepad.ui.notelist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.anwar.mynotepad.datarepository.database.NoteContactDatabase
import com.anwar.mynotepad.databinding.FragmentItemListBinding
import com.anwar.mynotepad.utils.Popup
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 */
class ContactListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Layout binding
        val binding = FragmentItemListBinding.inflate(layoutInflater)

        val dataSource = NoteContactDatabase.getInstance(this.requireContext()).noteContactDao

        val application = requireNotNull(this.activity).application

        val itemListViewModel by viewModels<ContactListViewModel> {
            ContactListViewModelFactory(
                dataSource,
                application
            )
        }


        binding.itemListViewModel = itemListViewModel
        binding.lifecycleOwner = this


        val adapter =
            ContactItemAdapter(
                ContactItemListener { itemId ->
                    val action =
                        ContactListFragmentDirections.actionItemListFragmentToItemDetailFragment(
                            itemId
                        )
                    this.findNavController().navigate(action)
                })

        binding.noteListRecyclerview.adapter = adapter

        itemListViewModel.noteItems.observe(this, Observer {
            adapter.submitList(it)
            if (it.isNullOrEmpty()){
                val action =
                    ContactListFragmentDirections.actionItemListFragmentToHomeFragment()
                this.findNavController().navigate(action)
            }
        })


        binding.addAnotherItem.setOnClickListener {
            Popup.createPopUp(layoutInflater,requireContext(),this)
        }
        Timber.i( "my Message List Fragment")

        return binding.root
    }

}