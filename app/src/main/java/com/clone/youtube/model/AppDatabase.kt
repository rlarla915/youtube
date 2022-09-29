package com.clone.youtube.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.clone.youtube.model.offline.OfflineVideo
import com.clone.youtube.model.offline.OfflineVideoDao

@Database(entities = [OfflineVideo::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun offlineVideoDao() : OfflineVideoDao
}