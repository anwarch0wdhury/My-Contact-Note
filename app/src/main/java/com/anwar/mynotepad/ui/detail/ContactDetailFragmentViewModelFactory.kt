package com.anwar.mynotepad.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anwar.mynotepad.datarepository.database.NoteContactDao

import java.lang.IllegalArgumentException


//why viewmodel factory- ViewModel have dependencies so we should pass this dependencies through the constructor
// (It is the best way to pass dependencies), so we can mock that dependencies and test ViewModel.


@Suppress("UNCHECKED_CAST")
class ContactDetailFragmentViewModelFactory (
    private val dataSource: NoteContactDao,
    private val itemId: Long
): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContactDetailFragmentViewModel::class.java)){
            return ContactDetailFragmentViewModel(
                dataSource,
                itemId
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}