package com.clone.youtube.model

import android.os.Parcelable
import android.view.View
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime

data class PlayerVideoInfoDAO(
    @SerializedName("videoUrl")
    var videoUrl : String,
    @SerializedName("playerVideoInfo")
    var playerVideoInfo: PlayerVideoInfo
)
