package com.anwar.mynotepad

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.anwar.mynotepad.datarepository.database.NoteContactDatabase
import com.anwar.mynotepad.datarepository.database.NoteContactDao
import com.anwar.mynotepad.datarepository.model.Contactdata

import org.junit.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/*
* Junit test
* */

@RunWith(AndroidJUnit4::class)
class ContactdataDatabaseTest {

    private lateinit var noteContactDao: NoteContactDao
    private lateinit var db: NoteContactDatabase

    @Before   //Executed before each test. It is used to prepare the test environment ( read input data, initialize the class).
    fun createDb(){
      val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context,
            NoteContactDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        noteContactDao = db.noteContactDao
    }

    @After  //Executed after each test. It is used to cleanup the test environment (e.g., delete temporary data, restore defaults).
    @Throws(IOException::class)
    fun closeDb(){
        db.close()
    }

    /** Unit testing assert equals.....
     * This tests the insert method of the DAO
     */
    @Test   //Identifies a method as a test method. test 1
    @Throws(Exception::class)
    fun insertAndGetItem(){
      val item = Contactdata(
          noteName = "fahim",
          noteDescription = "hiii",
          noteNumber = 5
      )
        noteContactDao.insert(item)

        val newItem = noteContactDao.getOneItem()
        assertEquals(newItem?.noteName,"fahim")
        assertEquals(newItem?.noteDescription,"hiii")
        assertEquals(newItem?.noteNumber,5)
    }

    @Test  //test 2
    fun deleteItem(){
        val item = Contactdata(
            noteName = "Rice",
            noteDescription = "hi",
            noteNumber = 1
        )
        val itemId = item.noteId
        noteContactDao.insert(item)
        noteContactDao.deleteItem(item)
        assertEquals(noteContactDao.getItemById(itemId),null)
    }
}

