package com.clone.youtube.model

import java.time.LocalDateTime

data class MainVideoListItem(
    var thumbnailUrl : Int,
    var videoUrl : String,
    var title : String,
    var view : Int,
    var time : LocalDateTime,
    var channel : Channel)
