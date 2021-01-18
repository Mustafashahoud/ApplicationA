package com.android.mustafa.applicationa.core.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.mustafa.applicationa.core.di.AppViewModelFactory
import com.android.mustafa.applicationa.core.di.ViewModelKey
import com.android.mustafa.applicationa.feature.NotificationViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NotificationViewModel::class)
    abstract fun bindMovieListFragmentViewModel(movieListViewModel: NotificationViewModel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory
}