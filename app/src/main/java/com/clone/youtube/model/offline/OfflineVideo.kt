package com.clone.youtube.model.offline

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.clone.youtube.model.Channel
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

@Entity
data class OfflineVideo(
    @PrimaryKey(autoGenerate = true)
    val uid : Long?,
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
) {
    constructor(videoUrl: String, thumbnailUrl: String, title: String, createTime: Long, view: Int, channelName: String, channelProfileUrl: String) : this(null, videoUrl, thumbnailUrl, title, createTime, view, channelName, channelProfileUrl)
}
