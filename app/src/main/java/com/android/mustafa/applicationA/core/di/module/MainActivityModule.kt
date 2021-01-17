package com.android.mustafa.applicationA.core.di.module

import com.android.mustafa.applicationA.feature.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

}
