package com.anwar.mynotepad.datarepository.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.anwar.mynotepad.utils.getCurrentSystemTime




@Entity(tableName = "note_table")
data class Contactdata(

    @PrimaryKey(autoGenerate = true)
    var noteId: Long = 0L,

    @ColumnInfo(name = "note_title")
    var noteName: String,

    @ColumnInfo(name = "note_description")
    var noteDescription: String,

    @ColumnInfo(name = "note_number")
    var noteNumber: Int,

    @ColumnInfo(name = "date_created")
    var dateNoteCreated: String = getCurrentSystemTime(
        System.currentTimeMillis()
    )
)

