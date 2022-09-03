package com.clone.youtube.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clone.youtube.model.MainVideoListItem
import com.clone.youtube.model.PlayerVideoInfo
import com.clone.youtube.model.VideoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayViewModel @Inject constructor(private val videoRepository: VideoRepository) : ViewModel() {
    val videoUrl = MutableLiveData<String>()
    val playerVideoInfo = MutableLiveData<PlayerVideoInfo>()
    val playerVideoInfoFromList = MutableLiveData<MainVideoListItem>()
    val playerVideoList = MutableLiveData<ArrayList<MainVideoListItem>>()
    val playWhenReady = MutableLiveData<Boolean>(true)
    val currentWindow = MutableLiveData<Int>(0)
    val playBackPosition = MutableLiveData<Long>(0L)

    fun loadPlayerVideoInfo(){
        viewModelScope.launch {
            videoRepository.getVideoInfo(videoUrl, playerVideoInfo)
        }
    }
}