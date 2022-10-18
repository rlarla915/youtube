package com.clone.youtube.extensions

fun Int.toLiteralString(): String {
    return when {
        this >= 1E8 -> "${String.format("%.1f", (this.toFloat() / 1E8))}억"
        this >= 1E4 -> "${String.format("%.1f", (this.toFloat() / 1E4))}만"
        this >= 1E3 -> "${String.format("%.1f", (this.toFloat() / 1E8))}천"
        else -> this.toString()
    }
}