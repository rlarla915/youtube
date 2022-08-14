package com.clone.youtube.model

import android.os.Parcelable
import android.view.View
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime

@Parcelize
data class Video(
    var thumbnailUrl: Any,
    var videoUrl: String,
    var title: String,
    var description: String,
    var createTime: LocalDateTime,
    var view: Int,
    var likes: Int,
    var channel: Channel,
    var comments: List<Comment>
) : Parcelable
