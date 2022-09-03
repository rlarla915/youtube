package com.clone.youtube.model

import android.os.Parcelable
import android.view.View
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime

data class PlayerVideoInfo(
    @SerializedName("like")
    var like : Int,
    @SerializedName("bestComment")
    var bestComment: BestComment,
    @SerializedName("numComments")
    var numComments: Int
)
