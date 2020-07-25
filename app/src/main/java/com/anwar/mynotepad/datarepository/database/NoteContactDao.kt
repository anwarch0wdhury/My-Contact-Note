package com.anwar.mynotepad.datarepository.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.anwar.mynotepad.datarepository.model.Contactdata


/**
 * This creates the Database Access Object popularly known as a 'Dao'
 * to insert, query, update, and delete from the Contact Note Database
 * DAO provides some specific data operations without exposing details of the database.
 */
@Dao
interface NoteContactDao {

    @Insert
    fun insert(contactdata: Contactdata)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(contactdata: Contactdata)

    @Delete
    fun deleteItem(contactdata: Contactdata)

    @Query("SELECT * FROM note_table ORDER BY noteId ASC")
    fun getAllItems() :LiveData<List<Contactdata>>

    @Query("SELECT * FROM note_table ORDER BY noteId ASC LIMIT 1")
    fun getOneItem(): Contactdata?

    @Query("SELECT * FROM note_table ORDER BY noteId ASC")
    fun getRawList() :List<Contactdata>

    @Query("SELECT * FROM note_table WHERE noteId=:id ")
    fun getItemById(id: Long) : Contactdata
}