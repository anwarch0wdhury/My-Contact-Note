package com.anwar.mynotepad.datarepository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.anwar.mynotepad.datarepository.model.Contactdata


/**
 * This represents the Room Database for the Contacts.
 * It creates a new Database if an instance does not exist and returns a
 * reference to an already existing Database.
 */
@Database(entities = [Contactdata::class],version = 1, exportSchema = false)
abstract class NoteContactDatabase : RoomDatabase(){

    abstract val noteContactDao: NoteContactDao

    companion object{

        @Volatile
        private var INSTANCE: NoteContactDatabase? = null

        fun getInstance(context: Context) : NoteContactDatabase {
            synchronized(this){
                var instance =
                    INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(context.applicationContext
                        , NoteContactDatabase::class.java,"note_database")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }

    }
}