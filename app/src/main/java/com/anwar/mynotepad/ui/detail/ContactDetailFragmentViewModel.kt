package com.anwar.mynotepad.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anwar.mynotepad.datarepository.database.NoteContactDao
import kotlinx.coroutines.*
import com.anwar.mynotepad.datarepository.model.Contactdata as Item

class ContactDetailFragmentViewModel(
    val dataSource: NoteContactDao,
    val itemId: Long
) : ViewModel(){

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


    private val _item = MutableLiveData<Item>()
    val item: LiveData<Item>
    get() = _item

    init {
        getItem()
    }

    /**
     * This gets the item whose details are to be displayed in the Details Fragment.
     * It gets the item from the [dataSource] using the item's [itemId] on a background
     * thread.
     */
    private fun getItem() {
        uiScope.launch {
            withContext(Dispatchers.IO){
                _item.postValue(dataSource.getItemById(itemId))
            }
        }
    }
}