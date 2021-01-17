package com.android.mustafa.applicationA.service

import android.content.Intent
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import com.android.mustafa.applicationA.service.CustomNotificationListenerService.d.action

class CustomNotificationListenerService : NotificationListenerService() {
    /*
        These are the package names of the apps. for which we want to
        listen the notifications
     */
    private object ApplicationPackageNames {
        const val FACEBOOK_PACK_NAME = "com.facebook.katana"
        const val FACEBOOK_MESSENGER_PACK_NAME = "com.facebook.orca"
        const val WHATSAPP_PACK_NAME = "com.whatsapp"
        const val INSTAGRAM_PACK_NAME = "com.instagram.android"
    }

    object d {
        const val action = "com.android.mustafa.application"
    }

    /*
        These are the return codes we use in the method which intercepts
        the notifications, to decide whether we should do something or not
     */
    object InterceptedNotificationCode {
        const val FACEBOOK_CODE = 1
        const val WHATSAPP_CODE = 2
        const val INSTAGRAM_CODE = 3
        const val OTHER_NOTIFICATIONS_CODE = 4 // We ignore all notification with code == 4
    }

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        val notificationCode = matchNotificationCode(sbn)

        if (notificationCode != InterceptedNotificationCode.OTHER_NOTIFICATIONS_CODE) {
            val intent = Intent(action)
            intent.putExtra("Notification Code", notificationCode)
            sendBroadcast(intent)
        }
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification) {
        val notificationCode = matchNotificationCode(sbn)
        if (notificationCode != InterceptedNotificationCode.OTHER_NOTIFICATIONS_CODE) {
            val activeNotifications = this.activeNotifications
            if (activeNotifications != null && activeNotifications.isNotEmpty()) {
                for (activeNotification in activeNotifications) {
                    if (notificationCode == matchNotificationCode(activeNotification)) {
                        val intent = Intent("com.github.chagall.notificationlistenerexample")
                        intent.putExtra("Notification Code", notificationCode)
                        sendBroadcast(intent)
                        break
                    }
                }
            }
        }
    }

//    /**
//     * isNotificationAccessEnabled can be used in case we want to ask the user to promote notification permission every time the app is restarted
//     * However i will be using another method  see []
//     */
//    private var _isNotificationAccessEnabled = false
//    val isNotificationAccessEnabled get() = _isNotificationAccessEnabled
//
//    override fun onListenerConnected() {
//        _isNotificationAccessEnabled = true
//    }
//
//    override fun onListenerDisconnected() {
//        _isNotificationAccessEnabled = false
//    }


    private fun matchNotificationCode(sbn: StatusBarNotification): Int {
        val packageName = sbn.packageName
        return when (packageName) {
            ApplicationPackageNames.FACEBOOK_PACK_NAME, ApplicationPackageNames.FACEBOOK_MESSENGER_PACK_NAME -> InterceptedNotificationCode.FACEBOOK_CODE
            ApplicationPackageNames.INSTAGRAM_PACK_NAME -> InterceptedNotificationCode.INSTAGRAM_CODE
            ApplicationPackageNames.WHATSAPP_PACK_NAME -> InterceptedNotificationCode.WHATSAPP_CODE
            else -> InterceptedNotificationCode.OTHER_NOTIFICATIONS_CODE
        }
    }
}