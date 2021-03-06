package com.android.mustafa.applicationa.core.di.module

import android.app.Application
import com.android.mustafa.applicationa.core.db.BllocDao
import com.android.mustafa.applicationa.core.db.BllocDb
import com.android.mustafa.applicationa.core.db.BllocDbFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideBitcoinChartDb(application: Application): BllocDb {
        return BllocDbFactory.createBllocDb(application)
    }

    @Provides
    @Singleton
    fun provideMovieDao(db: BllocDb): BllocDao {
        return db.bllocDao()
    }

}