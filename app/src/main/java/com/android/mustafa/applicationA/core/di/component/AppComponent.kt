package com.android.mustafa.applicationA.core.di.component

import android.app.Application
import com.android.mustafa.applicationA.BllocApp
import com.android.mustafa.applicationA.core.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        MainActivityModule::class,
        DatabaseModule::class,
        ContentProviderModule::class,
        AppModule::class
    ]
)
interface AppComponent {

    fun inject(bllocApp: BllocApp)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}
