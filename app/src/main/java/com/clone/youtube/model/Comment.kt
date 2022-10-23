package com.clone.youtube.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.threeten.bp.LocalDateTime

@Parcelize
data class Comment(
    val channel: Channel,
    val time: LocalDateTime,
    val likes: Int,
    val text: String
) : Parcelable