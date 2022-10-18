package com.clone.youtube.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.threeten.bp.LocalDateTime

@Parcelize
data class Comment(
    var channel: Channel,
    var time: LocalDateTime,
    var likes: Int,
    var text: String
) : Parcelable