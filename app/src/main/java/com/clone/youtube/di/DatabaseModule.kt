package com.clone.youtube.di

import android.content.Context
import androidx.room.Room
import com.clone.youtube.model.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context) : AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "offline_db"
        ).fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideOfflineVideoDao(appDatabase: AppDatabase) = appDatabase.offlineVideoDao()
}