package com.android.mustafa.applicationa.provider

import android.content.*
import android.database.Cursor
import android.net.Uri
import androidx.annotation.Nullable
import com.android.mustafa.applicationa.core.db.BllocDb
import com.android.mustafa.applicationa.feature.model.NotificationEntity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import java.util.concurrent.Callable
import javax.inject.Inject

class CustomContentProvider : ContentProvider(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var db: BllocDb

    companion object {

        /** The authority of this content provider.  */
        const val AUTHORITY = "com.android.mustafa.applicationa.provider"
        private const val TABLE_NAME = NotificationEntity.TABLE_NAME


        // Will be used in the resolver

        // The URI for the notifications table.
        val URI_NOTIFICATION: Uri = Uri.parse(
            "content://$AUTHORITY/notifications"
        )


        /**The match code for some items in the Notification table.  */
        private const val CODE_Notification_DIR = 1

        /** The match code for an item in the Notification table.  */
        private const val CODE_Notification_ITEM = 2

        /** The URI matcher.  */
        private val MATCHER = UriMatcher(UriMatcher.NO_MATCH)

        init {
            MATCHER.addURI(AUTHORITY, TABLE_NAME, CODE_Notification_DIR)
            MATCHER.addURI(AUTHORITY, "$TABLE_NAME/*", CODE_Notification_ITEM)
        }
    }


    override fun onCreate(): Boolean {
        // Inject CustomContentProvider to be able to do field injection for db
        AndroidInjection.inject(this)
        return true
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String?>?
    ): Int {

        return when (MATCHER.match(uri)) {
            CODE_Notification_DIR -> throw java.lang.IllegalArgumentException("Invalid URI, cannot update without ID$uri")
            CODE_Notification_ITEM -> {
                val context = context ?: return 0
                val notification = values?.let { NotificationEntity.fromContentValues(it) }!!
                val count: Int = db.bllocDao().update(notification)
                context.contentResolver.notifyChange(uri, null)
                count
            }
            else -> throw java.lang.IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun delete(
        uri: Uri, selection: String?,
        selectionArgs: Array<String?>?
    ): Int {

        return when (MATCHER.match(uri)) {
            CODE_Notification_DIR -> {
                val context = context ?: return 0
                val count: Int = db.bllocDao().deleteAll()
                context.contentResolver.notifyChange(uri, null)
                count
            }
            CODE_Notification_ITEM -> {
                val context = context ?: return 0
                val count: Int = db.bllocDao().deleteBy(ContentUris.parseId(uri))
                context.contentResolver.notifyChange(uri, null)
                count
            }
            else -> throw java.lang.IllegalArgumentException("Unknown URI: $uri")
        }
    }


    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return when (MATCHER.match(uri)) {
            CODE_Notification_DIR -> {
                val context = context ?: return null
                val id: Long =
                    db.bllocDao().insert(values?.let { NotificationEntity.fromContentValues(it) }!!)
                context.contentResolver.notifyChange(uri, null)
                ContentUris.withAppendedId(uri, id)
            }
            CODE_Notification_ITEM -> throw java.lang.IllegalArgumentException("Invalid URI, cannot insert with ID: $uri")
            else -> throw java.lang.IllegalArgumentException("Unknown URI: $uri")
        }
    }

    @Nullable
    override fun query(
        uri: Uri, @Nullable projection: Array<String>?, @Nullable selection: String?,
        @Nullable selectionArgs: Array<String>?, @Nullable sortOrder: String?
    ): Cursor? {

        val code = MATCHER.match(uri)
        return if (code == CODE_Notification_DIR || code == CODE_Notification_ITEM) {
            val context = context ?: return null

            val dao = db.bllocDao()

            val cursor: Cursor
            cursor = if (code == CODE_Notification_DIR) {
                dao.getAllNotifications()
            } else {
                dao.getNotificationBy(ContentUris.parseId(uri))
            }
            cursor.setNotificationUri(context.contentResolver, uri)
            cursor
        } else {
            throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    @Nullable
    override fun getType(uri: Uri): String {
        return when (MATCHER.match(uri)) {
            CODE_Notification_DIR -> "vnd.android.cursor.dir/$AUTHORITY.$TABLE_NAME"
            CODE_Notification_ITEM -> "vnd.android.cursor.item/$AUTHORITY.$TABLE_NAME"
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }


    @Throws(OperationApplicationException::class)
    override fun applyBatch(
        operations: ArrayList<ContentProviderOperation?>
    ): Array<ContentProviderResult?> {
        context ?: return arrayOfNulls(0)
        return db.runInTransaction(Callable<Array<ContentProviderResult?>> {
            super.applyBatch(
                operations
            )
        }) ?: arrayOf()
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

}