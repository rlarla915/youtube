package com.clone.youtube.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.clone.youtube.model.offline.OfflineVideo
import com.clone.youtube.model.offline.OfflineVideoDao

@Database(entities = [OfflineVideo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun offlineVideoDao() : OfflineVideoDao

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (instance == null){
                synchronized(AppDatabase::class) {
                    instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "database.db").build()
                }
            }
            return instance!!
        }
    }
}