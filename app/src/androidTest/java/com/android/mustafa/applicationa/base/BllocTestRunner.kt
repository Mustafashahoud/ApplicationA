package com.android.mustafa.applicationa.base

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

/**
 *  custom [BllocTestRunner]
 */
@Suppress("unused")
class BllocTestRunner : AndroidJUnitRunner() {
    @Throws(
        InstantiationException::class,
        IllegalAccessException::class,
        ClassNotFoundException::class
    )
    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        return super.newApplication(cl, TestBllocApp::class.java.name, context)
    }
}