package com.clone.youtube.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clone.youtube.model.MainVideoListItem
import com.clone.youtube.model.PlayerVideoInfo
import com.clone.youtube.model.VideoRepository
import com.clone.youtube.ui.play.MediaPlayer.MediaPlayerImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayViewModel @Inject constructor(private val videoRepository: VideoRepository) : ViewModel() {
    val playerVideoInfo = MutableLiveData<PlayerVideoInfo>()
    val playerVideoInfoFromList = MutableLiveData<MainVideoListItem>()
    val playerVideoList = MutableLiveData<ArrayList<MainVideoListItem>>()
    val playWhenReady = MutableLiveData<Boolean>(true)
    val currentWindow = MutableLiveData<Int>(0)
    val playBackPosition = MutableLiveData<Long>(0L)

    private val mediaPlayer = MediaPlayerImpl()

    fun getMediaPlayer() = mediaPlayer

    fun play() {
        mediaPlayer.play(playerVideoInfoFromList.value?.videoUrl!!, playWhenReady.value!!, currentWindow.value!!, playBackPosition.value!!)

    }

    fun release() = mediaPlayer.releasePlayer(playWhenReady, currentWindow, playBackPosition)

    /*
    fun createVideoUrl(video: ApiVideo) =
        "https://res.cloudinary.com/demo/video/${video.type}/v${video.version}/${video.publicId}.${video.format}"

     */

    fun loadPlayerVideoInfo(){
        viewModelScope.launch {
            videoRepository.getVideoInfo(playerVideoInfo)
        }
    }

}