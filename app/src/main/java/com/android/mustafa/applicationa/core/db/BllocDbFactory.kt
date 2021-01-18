package com.android.mustafa.applicationa.core.db

import android.app.Application
import androidx.room.Room


/**
 * Provide "create" method to create instance of [BllocDb]
 */
object BllocDbFactory {

    fun createBllocDb(application: Application): BllocDb {
        return Room
            .databaseBuilder(application, BllocDb::class.java, "Blloc.db")
            .fallbackToDestructiveMigration()
            .build()
    }

}