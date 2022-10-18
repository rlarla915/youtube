package com.clone.youtube.model

import com.google.gson.annotations.SerializedName

data class PlayerVideoInfo(
    @SerializedName("like")
    var like: Int,
    @SerializedName("bestComment")
    var bestComment: BestComment,
    @SerializedName("numComments")
    var numComments: Int
)
