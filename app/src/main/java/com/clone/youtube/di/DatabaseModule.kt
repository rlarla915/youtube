package com.clone.youtube.di

import android.content.Context
import com.clone.youtube.model.AppDatabase
import com.clone.youtube.model.offline.OfflineVideoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
/*
@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideOfflineVideoDao(appDatabase: AppDatabase): OfflineVideoDao {
        return appDatabase.offlineVideoDao()
    }
}

 */