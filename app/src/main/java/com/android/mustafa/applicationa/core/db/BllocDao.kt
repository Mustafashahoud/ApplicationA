package com.android.mustafa.applicationa.core.db

import android.database.Cursor
import androidx.room.*
import com.android.mustafa.applicationa.feature.model.NotificationEntity

@Dao
interface BllocDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(notification: NotificationEntity): Long

    @Update
    fun update(notification: NotificationEntity): Int

    @Query("SELECT * FROM notifications ORDER BY importance DESC")
    fun getAllNotifications(): Cursor

    @Query("SELECT * FROM notifications WHERE _id = :id")
    fun getNotificationBy(id: Long): Cursor

    @Query("DELETE FROM notifications WHERE _id = :id")
    fun deleteBy(id: Long) : Int

    @Query("DELETE FROM notifications")
    fun deleteAll() : Int

}

