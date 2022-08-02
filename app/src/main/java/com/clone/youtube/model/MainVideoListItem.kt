package com.clone.youtube.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime

@Parcelize
data class MainVideoListItem(
    var thumbnailUrl : Int,
    var videoUrl : String,
    var title : String,
    var view : Int,
    var likes : Int,
    var time : LocalDateTime,
    var channel : Channel,
    var comments : ArrayList<Comment>) : Parcelable
