package com.clone.youtube.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import org.threeten.bp.LocalDateTime

@Parcelize
data class VideoListItem(
    @SerializedName("id")
    var id: String,
    @SerializedName("videoUrl")
    var videoUrl: String,
    @SerializedName("thumbnailUrl")
    var thumbnailUrl: String,
    @SerializedName("title")
    var title: String,
    @SerializedName("createTime")
    var createTime: LocalDateTime,
    @SerializedName("view")
    var view: Int,
    @SerializedName("channel")
    var channel: Channel
) : Parcelable
