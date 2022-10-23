package com.clone.youtube.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import org.threeten.bp.LocalDateTime

@Parcelize
data class VideoListItem(
    @SerializedName("id")
    val id: String,
    @SerializedName("videoUrl")
    val videoUrl: String,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("createTime")
    val createTime: LocalDateTime,
    @SerializedName("view")
    val view: Int,
    @SerializedName("channel")
    val channel: Channel
) : Parcelable
