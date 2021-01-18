package com.android.mustafa.applicationa.core.util

import android.content.ComponentName
import android.content.Context
import android.provider.Settings

class ServiceUtil {

    /**
     * @param packageName the name of this application's package.
     * @param context – to get the UI component to access the database with
     * @param name to look up in the table
     */
    fun isServiceEnabled(
        context: Context,
        packageName: String,
        name: String
    ): Boolean {

        val flat = Settings.Secure.getString(context.contentResolver, name)
        if (flat.isNotEmpty()) {
            val names = flat.split(regex = ":".toRegex()).toTypedArray()
            for (index in names.indices) {
                val componentName: ComponentName? = ComponentName.unflattenFromString(names[index])
                componentName?.let {
                    if (packageName == componentName.packageName) {
                        return true
                    }
                }
            }
        }
        return false
    }
}