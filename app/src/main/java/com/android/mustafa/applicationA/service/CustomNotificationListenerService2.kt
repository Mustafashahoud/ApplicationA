package com.android.mustafa.applicationA.service

import android.app.Activity
import android.content.Intent
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.android.mustafa.applicationA.feature.NotificationUtil

class CustomNotificationListenerService2 : NotificationListenerService() {

    override fun onListenerConnected() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationUtil.createNotificationChannels(this)
        }
        NotificationUtil.createNotification(this, title = "title1", message = "message1")
        NotificationUtil.createNotification(this, title = "title2", message = "message2")
        NotificationUtil.createNotification(this, title = "title3", message = "message3")
    }


    /**
     * New Notn Added Callback
     */
    override fun onNotificationPosted(newNotification: StatusBarNotification) {
        sendNewResultOnUI(newNotification)
    }

    /**
     * Notn Removed callback
     */
    override fun onNotificationRemoved(removedNotification: StatusBarNotification) {
        sendRemoveResultOnUI(removedNotification)
    }

    private fun sendNewResultOnUI(statusBarNotification: StatusBarNotification) {
        val resultIntent = Intent(UPDATE_UI_NEW_ACTION)
        resultIntent.putExtra(RESULT_KEY_NEW, Activity.RESULT_OK)
        resultIntent.putExtra(RESULT_VALUE_NEW, statusBarNotification)
        LocalBroadcastManager.getInstance(this).sendBroadcastSync(resultIntent)
    }

    private fun sendRemoveResultOnUI(statusBarNotification: StatusBarNotification) {
        val resultIntent = Intent(UPDATE_UI_REMOVE_ACTION)
        resultIntent.putExtra(RESULT_KEY_REMOVE, Activity.RESULT_OK)
        resultIntent.putExtra(RESULT_VALUE_REMOVE, statusBarNotification)
        LocalBroadcastManager.getInstance(this).sendBroadcastSync(resultIntent)
    }


    companion object {
        //Update UI actions
        const val UPDATE_UI_REMOVE_ACTION = "UPDATE_UI_REMOVE_ACTION"
        const val UPDATE_UI_NEW_ACTION = "UPDATE_UI_NEW__ACTION"

        const val PACKAGE_NAME = "com.android.mustafa.applicationA"

        // Bundle Key Value Pair
        const val RESULT_KEY_NEW = "readResultKeyNew"
        const val RESULT_VALUE_NEW = "readResultValueNew"

        // Bundle Key Value Pair
        const val RESULT_KEY_REMOVE = "readResultKeyRemove"
        const val RESULT_VALUE_REMOVE = "readResultValueRemove"
    }
}