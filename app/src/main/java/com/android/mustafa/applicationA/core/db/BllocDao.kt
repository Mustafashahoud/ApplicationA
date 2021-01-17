package com.android.mustafa.applicationA.core.db

import android.database.Cursor
import androidx.room.*
import com.android.mustafa.applicationA.feature.model.NotificationEntity

@Dao
interface BllocDao {
    @Insert
    fun insert(notification: NotificationEntity): Long

    @Update
    fun update(notification: NotificationEntity): Int

    @Query("SELECT * FROM notifications")
    fun getAllNotifications(): Cursor

    @Query("SELECT * FROM notifications WHERE _id = :id")
    fun getNotificationBy(id: Long): Cursor

    @Query("DELETE FROM notifications WHERE _id = :id")
    fun deleteBy(id: Long) : Int

    @Query("DELETE FROM notifications")
    fun deleteAll() : Int

}

