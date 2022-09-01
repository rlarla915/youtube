package com.clone.youtube.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Channel(@SerializedName("name")
                   var name : String,
                   @SerializedName("profileUrl")
                   var profileUrl : String,
                   @SerializedName("subscribe")
                   var subscribe : Int) : Parcelable