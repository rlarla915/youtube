package com.clone.youtube.model

import android.os.Parcelable
import android.view.View
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime

data class MainVideoList(
    val mainVideoList : List<MainVideoListItem>
)
