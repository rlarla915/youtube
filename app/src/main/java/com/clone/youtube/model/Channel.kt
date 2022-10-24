package com.clone.youtube.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Channel(
    @SerializedName("name")
    val name: String,
    @SerializedName("profileUrl")
    val profileUrl: String,
    @SerializedName("subscribe")
    val subscribe: Int
) : Parcelable