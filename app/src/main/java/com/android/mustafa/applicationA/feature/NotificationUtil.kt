package com.android.mustafa.applicationA.feature

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import com.android.mustafa.applicationA.R
import com.android.mustafa.applicationA.feature.NotificationUtil.SocialPackageNames.FACEBOOK_PACKAGE
import com.android.mustafa.applicationA.feature.NotificationUtil.SocialPackageNames.INSTAGRAM_PACKAGE
import com.android.mustafa.applicationA.feature.NotificationUtil.SocialPackageNames.LINKEDIN_PACKAGE
import com.android.mustafa.applicationA.feature.NotificationUtil.SocialPackageNames.MASSENGER_PACKAGE
import com.android.mustafa.applicationA.feature.NotificationUtil.SocialPackageNames.TWITTER_PACKAGE
import com.android.mustafa.applicationA.feature.NotificationUtil.SocialPackageNames.WHATSAPP_PACKAGE
import com.android.mustafa.applicationA.feature.model.Category
import java.util.concurrent.atomic.AtomicInteger


object NotificationUtil {
     fun getNotificationCategory(packageName: String): Category {
        return when (packageName) {
            FACEBOOK_PACKAGE -> Category.SOCIAL
            MASSENGER_PACKAGE -> Category.SOCIAL
            WHATSAPP_PACKAGE -> Category.SOCIAL
            INSTAGRAM_PACKAGE -> Category.SOCIAL
            LINKEDIN_PACKAGE -> Category.SOCIAL
            TWITTER_PACKAGE -> Category.SOCIAL
            else -> Category.OTHER
        }
    }

     object SocialPackageNames {
        const val FACEBOOK_PACKAGE = "com.facebook.katana"
        const val MASSENGER_PACKAGE = "com.facebook.orca"
        const val WHATSAPP_PACKAGE = "com.whatsapp"
        const val INSTAGRAM_PACKAGE = "com.instagram.android"
        const val LINKEDIN_PACKAGE = "com.linkedin.android"
        const val TWITTER_PACKAGE = "com.twitter.android"
    }

    const val CHANNEL_1_ID = "channel1"
    const val CHANNEL_2_ID = "channel2"

    fun createNotification(context: Context, title: String, message: String) {
        val notificationManager = NotificationManagerCompat.from(context);
        val notification: Notification = NotificationCompat.Builder(context, CHANNEL_1_ID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_SYSTEM)
            .build()
        notificationManager.notify(NotificationID.id, notification);
    }


    @RequiresApi(Build.VERSION_CODES.O)
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