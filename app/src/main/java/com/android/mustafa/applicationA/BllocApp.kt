package com.android.mustafa.applicationA

import android.app.Application
import android.content.Context
import com.android.mustafa.applicationA.core.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import timber.log.Timber
import javax.inject.Inject

class BllocApp : Application(), HasAndroidInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    // Very important to use attachBaseContext instead of onCreate()
    //  onCreate() provider is called before onCreate() application
    // https://stackoverflow.com/questions/23521083/inject-database-in-a-contentprovider-with-dagger/44413873#44413873
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)

        AppInjector.init(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

    }


    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }
}