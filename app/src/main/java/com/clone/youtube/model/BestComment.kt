package com.clone.youtube.model

import com.google.gson.annotations.SerializedName

data class BestComment(
    @SerializedName("channelProfileUrl")
    val channelProfileUrl: String,
    @SerializedName("text")
    val text: String
)