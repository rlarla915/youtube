package com.clone.youtube.model

import com.google.gson.annotations.SerializedName

data class PlayerVideoInfo(
    @SerializedName("like")
    val like: Int,
    @SerializedName("bestComment")
    val bestComment: BestComment,
    @SerializedName("numComments")
    val numComments: Int
)
