package com.anwar.mynotepad.utils

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.anwar.mynotepad.ui.home.HomeFragmentViewModel
import com.anwar.mynotepad.ui.home.HomeFragmentViewModelFactory
import com.anwar.mynotepad.R
import com.anwar.mynotepad.datarepository.database.NoteContactDatabase
import com.anwar.mynotepad.datarepository.model.Contactdata


class Popup {

    companion object {
        /**
         * This shows the custom dialog for adding a new item
         */
        @SuppressLint("InflateParams")
        fun createPopUp(
            layoutInflater: LayoutInflater,
            context: Context,
            fragment: Fragment
        ): View {
            val builder = AlertDialog.Builder(context)
            val alertDialog: AlertDialog
            val view = layoutInflater.inflate(R.layout.create_new_item, null)
            builder.setView(view)
            alertDialog = builder.create()
            alertDialog.show()

            view.findViewById<Button>(R.id.btn_exit).setOnClickListener {
                alertDialog.dismiss()
            }
            view.findViewById<Button>(R.id.btn_save).setOnClickListener {
                val dataSource = NoteContactDatabase.getInstance(context).noteContactDao

                val homeFragmentViewModelFactory =
                    HomeFragmentViewModelFactory(
                        dataSource
                    )

                val homeFragmentViewModel =
                    ViewModelProviders.of(fragment, homeFragmentViewModelFactory)
                        .get(HomeFragmentViewModel::class.java)

                try {
                    val name =
                        view.findViewById<EditText>(R.id.et_noteName)?.text.toString()
                    val description =
                        view.findViewById<EditText>(R.id.et_noteDescription)?.text.toString()
                    val number =
                        view.findViewById<EditText>(R.id.et_noteNumber)?.text.toString().toInt()
                    val item = Contactdata(

                        noteName = name,
                        noteDescription = description,
                        noteNumber = number

                    )
                    homeFragmentViewModel.onSaveButtonPressed(item)
                    alertDialog.dismiss()
                } catch (e: Exception) {
                    // Must be safe
                    Toast.makeText(context, "Some fields are empty or input is error!", Toast.LENGTH_SHORT).show()
                }


            }

            return view
        }

    }
}