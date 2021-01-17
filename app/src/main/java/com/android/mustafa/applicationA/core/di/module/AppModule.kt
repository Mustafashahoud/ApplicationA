package com.android.mustafa.applicationA.core.di.module

import android.app.Application
import android.content.Context
import com.android.mustafa.applicationA.core.util.ServiceUtil
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideServiceUtil(): ServiceUtil {
        return ServiceUtil()
    }


}