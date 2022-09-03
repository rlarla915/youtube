package com.clone.youtube.model

import android.os.Parcelable
import android.view.View
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime

@Parcelize
data class MainVideoListItem(
    @SerializedName("id")
    var id: String,
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
