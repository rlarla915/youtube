package com.clone.youtube.extensions

import android.os.Build
import android.util.Log
import java.time.Duration
import java.time.LocalDateTime

fun LocalDateTime.toLiteralString() : String {
    val nowTime : LocalDateTime = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        LocalDateTime.now()
    } else {
        TODO("VERSION.SDK_INT < O")
    }
    val seconds= Duration.between(this, nowTime).seconds
    Log.d("XXXXXXX", (seconds >= 3600L).toString())
    return when{
        seconds >= 31536000L -> "${seconds/31536000L}년"
        seconds >= 2592000L -> "${seconds/2592000L}달"
        seconds >= 604800L*2 -> "${seconds/604800L}주"
        seconds >= 86400L -> "${seconds/86400L}일"
        seconds >= 3600L -> "${seconds/3600L}시간"
        seconds >= 60L -> "${seconds/60L}분"
        else -> seconds.toString()+"초"
    }
}