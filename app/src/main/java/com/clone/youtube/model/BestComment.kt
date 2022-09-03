package com.clone.youtube.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime

data class BestComment(
    @SerializedName("channelProfileUrl")
    var channelProfileUrl: String,
    @SerializedName("text")
    var text : String)