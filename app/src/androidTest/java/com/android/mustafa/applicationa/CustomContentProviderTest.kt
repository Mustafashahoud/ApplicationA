package com.android.mustafa.applicationa

import android.content.*
import android.os.RemoteException
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.android.mustafa.applicationa.feature.model.NotificationEntity.Companion.COLUMN_TITLE
import com.android.mustafa.applicationa.provider.CustomContentProvider
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.notNullValue
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*


/**
 * This test wont work for now as it needs a TestComponent and to fake and DataBaseModule
 *  I did not have enough time to do it.
 *  TODO mock DatabaseModule to get a in-memory database
 */

@RunWith(AndroidJUnit4::class)
@SmallTest
class CustomContentProviderTest : DbTest() {
    private lateinit var mContentResolver: ContentResolver


    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        mContentResolver = context.contentResolver
    }

    @Test
    fun notification_initiallyEmpty() {
        val cursor = mContentResolver.query(
            CustomContentProvider.URI_NOTIFICATION,
            arrayOf(COLUMN_TITLE),
            null,
            null,
            null
        )
        assertThat(cursor, notNullValue())
        assertThat(cursor!!.count, `is`(0))
        cursor.close()
    }

    @Test
    fun notification_insert() {
        val itemUri = mContentResolver.insert(
            CustomContentProvider.URI_NOTIFICATION,
            notificationWithTitle("title")
        )
        assertThat(itemUri, notNullValue())
        val cursor = mContentResolver.query(
            CustomContentProvider.URI_NOTIFICATION,
            arrayOf(COLUMN_TITLE),
            null,
            null,
            null
        )
        assertThat(cursor, notNullValue())
        assertThat(cursor!!.count, `is`(1))
        assertThat(cursor.moveToFirst(), `is`(true))
        assertThat(
            cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE)),
            `is`("title")
        )
        cursor.close()
    }

    @Test
    fun notification_update() {
        val itemUri = mContentResolver.insert(
            CustomContentProvider.URI_NOTIFICATION,
            notificationWithTitle("mustafa")
        )
        assertThat(itemUri, notNullValue())
        val count = mContentResolver.update(itemUri!!, notificationWithTitle("title"), null, null)
        assertThat(count, `is`(1))
        val cursor = mContentResolver.query(
            CustomContentProvider.URI_NOTIFICATION,
            arrayOf(COLUMN_TITLE),
            null,
            null,
            null
        )
        cursor?.let {
            assertThat(cursor, notNullValue())
            assertThat(cursor.count, `is`(1))
            assertThat(cursor.moveToFirst(), `is`(true))
            assertThat(
                cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE)),
                `is`("title")
            )
            cursor.close()
        }

    }

    @Test
    fun notification_delete() {
        val itemUri = mContentResolver.insert(
            CustomContentProvider.URI_NOTIFICATION,
            notificationWithTitle("title")
        )
        assertThat(itemUri, notNullValue())
        val cursor1 = mContentResolver.query(
            CustomContentProvider.URI_NOTIFICATION,
            arrayOf(COLUMN_TITLE),
            null,
            null,
            null
        )
        assertThat(cursor1, notNullValue())
        assertThat(cursor1!!.count, `is`(1))
        cursor1.close()
        val count = mContentResolver.delete(itemUri!!, null, null)
        assertThat(count, `is`(1))
        val cursor2 = mContentResolver.query(
            CustomContentProvider.URI_NOTIFICATION,
            arrayOf(COLUMN_TITLE),
            null,
            null,
            null
        )
        assertThat(cursor2, notNullValue())
        assertThat(cursor2!!.count, `is`(0))
        cursor2.close()
    }

    @Test
    fun notification_bulkInsert() {
        val count = mContentResolver.bulkInsert(
            CustomContentProvider.URI_NOTIFICATION, arrayOf(
                notificationWithTitle("title1"),
                notificationWithTitle("title2"),
                notificationWithTitle("title3")
            )
        )
        assertThat(count, `is`(3))
        val cursor = mContentResolver.query(
            CustomContentProvider.URI_NOTIFICATION,
            arrayOf(COLUMN_TITLE),
            null,
            null,
            null
        )
        assertThat(cursor, notNullValue())
        assertThat(cursor!!.count, `is`(3))
        cursor.close()
    }

    @Test
    @Throws(RemoteException::class, OperationApplicationException::class)
    fun notification_applyBatch() {
        val operations = ArrayList<ContentProviderOperation>()
        operations.add(
            ContentProviderOperation
                .newInsert(CustomContentProvider.URI_NOTIFICATION)
                .withValue(COLUMN_TITLE, "title1")
                .build()
        )
        operations.add(
            ContentProviderOperation
                .newInsert(CustomContentProvider.URI_NOTIFICATION)
                .withValue(COLUMN_TITLE, "title2")
                .build()
        )
        val results = mContentResolver.applyBatch(
            CustomContentProvider.AUTHORITY, operations
        )
        assertThat(results.size, `is`(2))
        val cursor = mContentResolver.query(
            CustomContentProvider.URI_NOTIFICATION,
            arrayOf(COLUMN_TITLE),
            null,
            null,
            null
        )
        assertThat(cursor, notNullValue())
        assertThat(cursor!!.count, `is`(2))
        assertThat(cursor.moveToFirst(), `is`(true))
        cursor.close()
    }

    private fun notificationWithTitle(name: String): ContentValues {
        val values = ContentValues()
        values.put(COLUMN_TITLE, name)
        return values
    }
}