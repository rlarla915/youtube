package com.clone.youtube.model.offline

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class OfflineVideo(
    @PrimaryKey
    val key: String,
    @ColumnInfo(name = "video_url")
    val videoUrl: String,
    @ColumnInfo(name = "thumbnail_url")
    val thumbnailUrl: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "create_time")
    val createTime: Long,
    @ColumnInfo(name = "view")
    val view: Int,
    @ColumnInfo(name = "channel_name")
    val channelName: String,
    @ColumnInfo(name = "channel_profile_url")
    val channelProfileUrl: String
)
