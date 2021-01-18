package com.android.mustafa.applicationa.feature.util

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.service.notification.StatusBarNotification
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import com.android.mustafa.applicationa.R
import com.android.mustafa.applicationa.feature.util.NotificationUtil.ChannelsUtil.CHANNEL_1_ID
import com.android.mustafa.applicationa.feature.util.NotificationUtil.ChannelsUtil.CHANNEL_2_ID
import com.android.mustafa.applicationa.feature.util.NotificationUtil.ChannelsUtil.IMPORTANCE_DEFAULT
import java.util.concurrent.atomic.AtomicInteger


object NotificationUtil {

    object ChannelsUtil {
        const val CHANNEL_1_ID = "channel1"
        const val CHANNEL_2_ID = "channel2"
        const val IMPORTANCE_DEFAULT = 3
    }


    fun getNotificationImportant(
        context: Context,
        statusBarNotification: StatusBarNotification
    ): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel = manager.getNotificationChannel(statusBarNotification.notification.channelId)
            channel.importance
        } else {
            IMPORTANCE_DEFAULT
        }
    }

    fun getNotificationTitle(statusBarNotification: StatusBarNotification): String? {
        return statusBarNotification.notification.extras.getString("android.title")
    }

    fun getNotificationMessage(statusBarNotification: StatusBarNotification): String? {
        return statusBarNotification.notification.extras.getString("android.text")
    }

    fun createNotification(context: Context, title: String, message: String, channelId: String) {
        val notificationManager = NotificationManagerCompat.from(context)
        val notification: Notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle(title)
            .setContentText(message)
            .setCategory(NotificationCompat.CATEGORY_SYSTEM)
            .build()
        notificationManager.notify(NotificationID.id, notification)
    }


    fun createNotificationChannels(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel1 = NotificationChannel(
                CHANNEL_1_ID,
                "Channel 1",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel1.description = "This is Channel 1"
            val channel2 = NotificationChannel(
                CHANNEL_2_ID,
                "Channel 2",
                NotificationManager.IMPORTANCE_LOW
            )
            channel2.description = "This is Channel 2"
            val manager: NotificationManager? = getSystemService(
                context,
                NotificationManager::class.java
            )
            manager?.createNotificationChannel(channel1)
            manager?.createNotificationChannel(channel2)
        }
    }

    object NotificationID {
        private val c: AtomicInteger = AtomicInteger(0)
        val id: Int
            get() = c.incrementAndGet()
    }

}