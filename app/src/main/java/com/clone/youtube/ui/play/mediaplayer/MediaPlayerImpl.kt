package com.clone.youtube.ui.play.mediaplayer

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem

//import androidx.media3.exoplayer.ExoPlayer
//import androidx.media3.common.MediaItem

class MediaPlayerImpl : MediaPlayer {

    private var exoPlayer: ExoPlayer? = null
    private lateinit var context: Context

    override fun getPlayer(context: Context): ExoPlayer {
        this.context = context
        initializePlayer()
        return exoPlayer!!
    }

    override fun play(
        url: String,
        playWhenReady: Boolean,
        currentWindow: Int,
        playBackPosition: Long
    ) {
        val mediaItem = MediaItem.fromUri(url)
        exoPlayer?.run {
            this.setMediaItem(mediaItem)
            this.playWhenReady = playWhenReady
            this.seekTo(currentWindow, playBackPosition)
            this.prepare()
        }
    }

    private fun initializePlayer() {
        exoPlayer = ExoPlayer.Builder(context).build()
    }

    override fun releasePlayer(
        playWhenReady: MutableLiveData<Boolean>,
        currentWindow: MutableLiveData<Int>,
        playBackPosition: MutableLiveData<Long>
    ) {
        playBackPosition.postValue(exoPlayer?.currentPosition)
        currentWindow.postValue(exoPlayer?.currentMediaItemIndex)
        playWhenReady.postValue(exoPlayer?.playWhenReady)
        exoPlayer?.release()
        exoPlayer = null
    }

}