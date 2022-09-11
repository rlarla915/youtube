package com.clone.youtube.model.offline

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface OfflineVideoDao {
    @Query("SELECT * FROM offlineVideo")
    fun getAllVideo() : ArrayList<OfflineVideo>

    @Insert
    fun insertAll(vararg offlineVideo: OfflineVideo)

    @Delete
    fun delete(offlineVideo: OfflineVideo)
}