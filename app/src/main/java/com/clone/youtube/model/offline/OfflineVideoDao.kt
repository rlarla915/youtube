package com.clone.youtube.model.offline

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface OfflineVideoDao {
    @Query("SELECT * FROM offlineVideo")
    suspend fun getAllVideo() : List<OfflineVideo>

    @Insert
    suspend fun insertAll(vararg offlineVideo: OfflineVideo)

    @Delete
    suspend fun delete(offlineVideo: OfflineVideo)
}