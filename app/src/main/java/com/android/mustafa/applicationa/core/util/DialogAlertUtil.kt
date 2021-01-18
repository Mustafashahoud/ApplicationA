package com.android.mustafa.applicationa.core.util

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import com.android.mustafa.applicationa.R

object DialogAlertUtil {

    /**
     * @param themeResId the style of the Dialog
     * @param context the Context to show the dialog
     * @param action intent action to move to
     * @param title the title of the dialog
     * @param message the message of the dialog
     */
    fun createAlertDialogForSpecificPermission(
        context: Context,
        themeResId: Int = Resources.ID_NULL,
        action: String,
        title: String,
        message: String
    ): AlertDialog {
        val alertDialogBuilder = AlertDialog.Builder(context, themeResId)
        alertDialogBuilder.setTitle(title)
        alertDialogBuilder.setMessage(message)
        alertDialogBuilder.setPositiveButton(
            R.string.yes
        ) { _, _ ->
            context.startActivity(Intent(action))
        }
        alertDialogBuilder.setNegativeButton(
            R.string.no
        ) { _, _ -> }
        return alertDialogBuilder.create()
    }
}