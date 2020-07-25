package com.anwar.mynotepad.ui.notelist

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anwar.mynotepad.datarepository.database.NoteContactDao


@Suppress("UNCHECKED_CAST")
class ContactListViewModelFactory(
    private val dataSource: NoteContactDao,
    private val application: Application) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContactListViewModel::class.java)) {
            return ContactListViewModel(
                dataSource,
                application
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}