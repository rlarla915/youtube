package com.clone.youtube.ui.play.MediaPlayer

import android.content.Context
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import com.clone.youtube.viewmodel.PlayViewModel
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.util.Util

class MediaPlayerImpl : MediaPlayer {

    private lateinit var exoPlayer: ExoPlayer
    private lateinit var context: Context

    override fun getPlayer(context: Context): ExoPlayer {
        this.context = context
        initializePlayer()
        return exoPlayer
    }


    override fun play(url: String, playWhenReady : Boolean, currentWindow : Int, playBackPosition : Long) {
        val mediaItem = MediaItem.fromUri(url)
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.playWhenReady = playWhenReady
        exoPlayer.seekTo(currentWindow, playBackPosition)
        exoPlayer.prepare()
    }

    private fun initializePlayer() {
        exoPlayer = ExoPlayer.Builder(context).build()
    }

    override fun releasePlayer(playWhenReady : MutableLiveData<Boolean>, currentWindow : MutableLiveData<Int>, playBackPosition : MutableLiveData<Long>) {
        playBackPosition.postValue(exoPlayer.currentPosition)
        currentWindow.postValue(exoPlayer.currentMediaItemIndex)
        playWhenReady.postValue(exoPlayer.playWhenReady)
        exoPlayer.release()
    }



}