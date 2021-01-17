package com.android.mustafa.applicationA.feature

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.database.Cursor
import android.os.Bundle
import android.service.notification.StatusBarNotification
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.android.mustafa.applicationA.R
import com.android.mustafa.applicationA.core.util.DialogAlertUtil
import com.android.mustafa.applicationA.core.util.ServiceUtil
import com.android.mustafa.applicationA.feature.model.NotificationEntity
import com.android.mustafa.applicationA.feature.model.NotificationEntity.Companion.COLUMN_ID
import com.android.mustafa.applicationA.feature.model.NotificationEntity.Companion.COLUMN_PACKAGE_NAME
import com.android.mustafa.applicationA.feature.model.NotificationEntity.Companion.COLUMN_PRIORITY
import com.android.mustafa.applicationA.feature.model.NotificationEntity.Companion.COLUMN_TIME
import com.android.mustafa.applicationA.feature.model.NotificationEntity.Companion.COLUMN_TITLE
import com.android.mustafa.applicationA.provider.CustomContentProvider.Companion.URI_NOTIFICATION
import com.android.mustafa.applicationA.service.CustomNotificationListenerService2.Companion.RESULT_KEY_NEW
import com.android.mustafa.applicationA.service.CustomNotificationListenerService2.Companion.RESULT_KEY_REMOVE
import com.android.mustafa.applicationA.service.CustomNotificationListenerService2.Companion.RESULT_VALUE_NEW
import com.android.mustafa.applicationA.service.CustomNotificationListenerService2.Companion.RESULT_VALUE_REMOVE
import com.android.mustafa.applicationA.service.CustomNotificationListenerService2.Companion.UPDATE_UI_NEW_ACTION
import com.android.mustafa.applicationA.service.CustomNotificationListenerService2.Companion.UPDATE_UI_REMOVE_ACTION
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasAndroidInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var serviceUtil: ServiceUtil

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<NotificationViewModel> { viewModelFactory }

    var cursor: Cursor? = null


    companion object {
        private const val ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners"
        private const val ACTION_NOTIFICATION_LISTENER_SETTINGS =
            "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // If the user did not turn the notification listener service on we prompt him to do so
        showDialogNotificationPermission()

//        LoaderManager.getInstance(this).initLoader<Cursor>(1, null, mLoaderCallbacks)

    }

    // Define the callback for what to do when data is received
    private val clientLooperReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val resultCodeNew = intent.getIntExtra(RESULT_KEY_NEW, RESULT_CANCELED)
            val resultCodeRemove = intent.getIntExtra(RESULT_KEY_REMOVE, RESULT_CANCELED)
            if (resultCodeNew == RESULT_OK) {
                val resultValueNew =
                    intent.getParcelableExtra<StatusBarNotification>(RESULT_VALUE_NEW)
                if (resultValueNew != null) {
                    val notificationToSend = buildNotification(resultValueNew)
                    viewModel.insert(notificationToSend)
                }
            } else if (resultCodeRemove == RESULT_OK) {
                val resultValueRemove =
                    intent.getParcelableExtra<StatusBarNotification>(RESULT_VALUE_REMOVE)
                if (resultValueRemove != null) {
                    val notificationToDelete = buildNotification(resultValueRemove)
                    viewModel.delete(notificationToDelete.id)
                }
            }
        }
    }

//    private val mLoaderCallbacks = object : LoaderManager.LoaderCallbacks<Cursor> {
//        override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
//            return CursorLoader(
//                applicationContext,
//                URI_NOTIFICATION, arrayOf(COLUMN_ID, COLUMN_PACKAGE_NAME, COLUMN_PRIORITY, COLUMN_TIME, COLUMN_TITLE),
//                null, null, null)
//        }
//
//        override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
//            cursor = data
//            if (cursor?.moveToPosition(1) == true) {
//                val title = cursor?.getString(cursor?.getColumnIndexOrThrow(COLUMN_TITLE)!!)
//                print(title)
//            }
//        }
//
//        override fun onLoaderReset(loader: Loader<Cursor>) {
//
//        }
//
//    }

    override fun onResume() {
        super.onResume()

        //Register to Broadcast for Updating UI
        LocalBroadcastManager.getInstance(this).registerReceiver(
            clientLooperReceiver,
            getIntentFilterWithTwoActions(UPDATE_UI_NEW_ACTION, UPDATE_UI_REMOVE_ACTION)
        )
    }

    @Suppress("SameParameterValue")
    private fun getIntentFilterWithTwoActions(action1: String, action2: String): IntentFilter {
        val intentFilter = IntentFilter()
        intentFilter.addAction(action1)
        intentFilter.addAction(action2)
        return intentFilter
    }

    @Suppress("DEPRECATION")
    private fun buildNotification(statusBarNotification: StatusBarNotification): NotificationEntity {
//        val title = statusBarNotification.notification.extras.getString("android.title")

        return NotificationEntity(
            statusBarNotification.id.toLong(),
            statusBarNotification.notification.extras.getString("android.title")
                ?: statusBarNotification.packageName,
            statusBarNotification.packageName,
            statusBarNotification.notification.priority,
            statusBarNotification.postTime
        )
    }

    private fun showDialogNotificationPermission() {
        if (!serviceUtil.isServiceEnabled(this, packageName, ENABLED_NOTIFICATION_LISTENERS)) {
            DialogAlertUtil.createAlertDialog(this, ACTION_NOTIFICATION_LISTENER_SETTINGS).show()
        }
    }

    override fun onDestroy() {

        viewModel.deleteAll()
        //UnRegister to Broadcast for Updating UI
        LocalBroadcastManager.getInstance(this).unregisterReceiver(clientLooperReceiver)
        super.onDestroy()
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
}
