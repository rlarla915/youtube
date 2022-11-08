package com.clone.youtube.ui.play.mediaplayer

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.google.android.exoplayer2.ExoPlayer

//import androidx.media3.exoplayer.ExoPlayer

interface MediaPlayer {
    fun play(url: String, playWhenReady: Boolean, currentWindow: Int, playBackPosition: Long)
    fun getPlayer(context: Context): ExoPlayer
    fun releasePlayer(
        playWhenReady: MutableLiveData<Boolean>,
        currentWindow: MutableLiveData<Int>,
        playBackPosition: MutableLiveData<Long>
    )
}