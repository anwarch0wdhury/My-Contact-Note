package com.anwar.mynotepad.ui.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anwar.mynotepad.datarepository.database.NoteContactDao


@Suppress("UNCHECKED_CAST")
class HomeFragmentViewModelFactory (
    private val dataSource: NoteContactDao
): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeFragmentViewModel::class.java)) {
            return HomeFragmentViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}