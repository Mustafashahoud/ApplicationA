package com.android.mustafa.applicationA.core.util

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import com.android.mustafa.applicationA.R

object DialogAlertUtil {

    /**
     * @param context the Context to show the dialog
     * @param action intent action tpo move to
     */
    fun createAlertDialog(context: Context, action: String): AlertDialog {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle(R.string.notification_listener_service)
        alertDialogBuilder.setMessage(R.string.notification_listener_service_explanation)
        alertDialogBuilder.setPositiveButton(
            R.string.yes
        ) { _, _ ->
            context.startActivity(Intent(action))
        }
        alertDialogBuilder.setNegativeButton(
            R.string.no
        ) { _, _ ->
            // If you choose to not enable the notification listener
            // the app. will not work as expected
        }
        return alertDialogBuilder.create()
    }
}