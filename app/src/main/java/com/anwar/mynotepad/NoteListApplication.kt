package com.anwar.mynotepad

import android.app.Application
import android.content.res.Configuration
import timber.log.Timber

/**
 * Created by Anwar on 2020-01-26.
 *
 */

class NoteListApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        /*
        Timber ---  log statements would automatically
        disable themselves when in production.
        Log statements automatically picked up the TAG/classname while logging.
        Timber is a loggin library inited of  Log.It is simple to use only disabling this line timber
        can disable all Timber in the app.
        */
        Timber.plant(Timber.DebugTree())
    }


}