package com.android.mustafa.applicationa.feature.model

import android.content.ContentValues
import android.provider.BaseColumns
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = NotificationEntity.TABLE_NAME)
data class NotificationEntity(
    @PrimaryKey
    @ColumnInfo(name = COLUMN_ID)
    var id: Long = 0,

    @ColumnInfo(name = COLUMN_TITLE)
    var title: String = "",

    @ColumnInfo(name = COLUMN_PACKAGE_NAME)
    var packageName: String = "",

    @ColumnInfo(name = COLUMN_MESSAGE)
    var message: String = "",

    @ColumnInfo(name = COLUMN_IMPORTANCE)
    var importance: Int = 0,

    @ColumnInfo(name = COLUMN_TIME)
    var time: Long = 0L
) {
    companion object {
        const val TABLE_NAME = "notifications"

        /** The name of the ID column.  */
        const val COLUMN_ID = BaseColumns._ID

        /** The name of the title column.  */
        const val COLUMN_TITLE = "title"

        /** The name of the packageName column.  */
        const val COLUMN_PACKAGE_NAME = "packageName"

        /** The name of the message column.  */
        const val COLUMN_MESSAGE = "message"

        /** The name of the importance column.  */
        const val COLUMN_IMPORTANCE = "importance"

        /** The name of the time column.  */
        const val COLUMN_TIME = "time"

        fun fromContentValues(values: ContentValues?): NotificationEntity {
            val notification = NotificationEntity()
            if (values != null && values.containsKey(COLUMN_ID)) {
                notification.id = values.getAsLong(COLUMN_ID)
            }
            if (values != null && values.containsKey(COLUMN_TITLE)) {
                notification.title = values.getAsString(COLUMN_TITLE)
            }
            if (values != null && values.containsKey(COLUMN_IMPORTANCE)) {
                notification.importance = values.getAsInteger(COLUMN_IMPORTANCE)
            }
            if (values != null && values.containsKey(COLUMN_PACKAGE_NAME)) {
                notification.packageName = values.getAsString(COLUMN_PACKAGE_NAME)
            }
            if (values != null && values.containsKey(COLUMN_MESSAGE)) {
                notification.message = values.getAsString(COLUMN_MESSAGE)
            }
            if (values != null && values.containsKey(COLUMN_TIME)) {
                notification.time = values.getAsLong(COLUMN_TIME)
            }
            return notification
        }

    }

}



