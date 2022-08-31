package com.clone.youtube.model

import android.os.Parcelable
import android.view.View
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime

@Parcelize
data class MainVideoListItem(
    var thumbnailUrl: String,
    var title: String,
    var createTime: LocalDateTime,
    var view: Int,
    var channel: Channel
) : Parcelable
