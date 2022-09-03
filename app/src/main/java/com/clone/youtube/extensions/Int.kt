package com.clone.youtube.extensions

import android.os.Build
import android.util.Log
import java.time.Duration
import java.time.LocalDateTime

fun Int.toLiteralString() : String {
    return when{
        this >= 1E8 -> "${String.format("%.1f", (this.toFloat()/1E8))}억"
        this >= 1E4 -> "${String.format("%.1f", (this.toFloat()/1E4))}만"
        this >= 1E3 -> "${String.format("%.1f", (this.toFloat()/1E8))}천"
        else -> this.toString()
    }
}