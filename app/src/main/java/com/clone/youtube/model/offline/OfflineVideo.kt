package com.clone.youtube.model.offline

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.clone.youtube.model.Channel
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

@Entity
data class OfflineVideo(
    @PrimaryKey
    val key: String,
    @ColumnInfo(name = "video_url")
    var videoUrl : String,
    @ColumnInfo(name = "thumbnail_url")
    var thumbnailUrl: String,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "create_time")
    var createTime: Long,
    @ColumnInfo(name = "view")
    var view: Int,
    @ColumnInfo(name = "channel_name")
    var channelName: String,
    @ColumnInfo(name = "channel_profile_url")
    var channelProfileUrl: String
)
