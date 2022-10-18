package com.clone.youtube.model

import com.google.gson.annotations.SerializedName

data class BestComment(
    @SerializedName("channelProfileUrl")
    var channelProfileUrl: String,
    @SerializedName("text")
    var text: String
)