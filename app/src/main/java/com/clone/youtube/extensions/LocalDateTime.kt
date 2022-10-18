package com.clone.youtube.extensions

import org.threeten.bp.Duration
import org.threeten.bp.LocalDateTime

fun LocalDateTime.toLiteralString(): String {
    val nowTime: LocalDateTime = LocalDateTime.now()
    val seconds = Duration.between(this, nowTime).seconds
    return when {
        seconds >= 31536000L -> "${seconds / 31536000L}년"
        seconds >= 2592000L -> "${seconds / 2592000L}달"
        seconds >= 604800L * 2 -> "${seconds / 604800L}주"
        seconds >= 86400L -> "${seconds / 86400L}일"
        seconds >= 3600L -> "${seconds / 3600L}시간"
        seconds >= 60L -> "${seconds / 60L}분"
        else -> seconds.toString() + "초"
    }
}