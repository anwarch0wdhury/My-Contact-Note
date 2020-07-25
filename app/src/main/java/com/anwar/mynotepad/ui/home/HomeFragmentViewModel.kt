package com.anwar.mynotepad.ui.home


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anwar.mynotepad.datarepository.database.NoteContactDao
import com.anwar.mynotepad.datarepository.model.Contactdata

import kotlinx.coroutines.*


class HomeFragmentViewModel (
    val database: NoteContactDao
): ViewModel(){

    //Coroutine Job
    private var viewModelJob = Job()

    //Coroutine scope
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _hasContent = MutableLiveData<Boolean>()
    val hasContent: LiveData<Boolean>
    get() = _hasContent


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    init {
        checkDataBase()
    }

    /**
     * This checks if there are any items already stored in the database.
     * The value of the mutable live data [_hasContent] is updated based on
     * if there is an item in the database or not.
     */
    private fun checkDataBase(){
        uiScope.launch {
            withContext(Dispatchers.IO){
                val oneItem = database.getOneItem()
                if (oneItem != null){
                    _hasContent.postValue(true)
                }else{
                    _hasContent.postValue(false)
                }
            }
        }
    }

    fun doneNavigating(){
        _hasContent.value = false
    }

    /**
     * This is triggered when the save button on the popup dialog is pressed.
     * Takes in a new item and sends it to the [insert] function to be saved in
     * the database on a coroutine.
     * @param item the item to be sent to the [insert] function
     */
    fun onSaveButtonPressed(item: Contactdata){
        uiScope.launch {
            insert(item)
        }
    }

    /**
     * This is a coroutine-friendly function that inserts a new item into the database.
     * @param item the item to be inserted into the database
     */
    private suspend fun insert(item: Contactdata){
        withContext(Dispatchers.IO){
            database.insert(item)
            _hasContent.postValue(true)
        }
    }
}