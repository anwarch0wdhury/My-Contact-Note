package com.anwar.mynotepad.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.anwar.mynotepad.datarepository.model.Contactdata


@BindingAdapter("noteName")
fun TextView.setNoteName(item: Contactdata?){
    item?.let {
        text = it.noteName
    }
}

@BindingAdapter("noteDescription")
fun TextView.setNoteDescription(item: Contactdata?){
    item?.let {
        text = it.noteDescription.toString()
    }
}

@BindingAdapter("noteNumber")
fun TextView.setNoteNumber(item: Contactdata?){
    item?.let {
        text = it.noteNumber.toString()
    }
}

@BindingAdapter("dateCreated")
fun TextView.setDateCreated(item: Contactdata?){
    item?.let {
        text = it.dateNoteCreated
    }
}