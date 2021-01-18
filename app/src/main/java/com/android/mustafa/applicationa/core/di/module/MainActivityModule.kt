package com.android.mustafa.applicationa.core.di.module

import com.android.mustafa.applicationa.feature.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

}
