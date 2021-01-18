package com.android.mustafa.applicationa.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.mustafa.applicationa.feature.model.NotificationEntity

@Database(
    entities = [NotificationEntity::class],
    version = 1, exportSchema = false
)
abstract class BllocDb : RoomDatabase() {
    abstract fun bllocDao(): BllocDao
}
