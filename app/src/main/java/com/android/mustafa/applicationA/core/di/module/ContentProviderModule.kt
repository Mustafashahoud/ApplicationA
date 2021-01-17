package com.android.mustafa.applicationA.core.di.module

import com.android.mustafa.applicationA.provider.CustomContentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ContentProviderModule {

    @ContributesAndroidInjector
    abstract fun bindCustomContentProvider(): CustomContentProvider

}
