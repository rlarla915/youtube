package com.clone.youtube.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Channel(var name : String, var profileUrl : Int, var subscribe : Int) : Parcelable