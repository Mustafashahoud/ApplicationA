package com.android.mustafa.applicationa.core.di.module

import com.android.mustafa.applicationa.provider.CustomContentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ContentProviderModule {

    @ContributesAndroidInjector
    abstract fun bindCustomContentProvider(): CustomContentProvider

}
