package com.clone.youtube.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.clone.youtube.model.VideoListItem
import com.clone.youtube.repository.video.VideoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val videoRepository: VideoRepository) :
    ViewModel() {

    private val _videoList: MutableLiveData<PagingData<VideoListItem>> =
        videoRepository.getVideoList("")
            .cachedIn(viewModelScope) as MutableLiveData<PagingData<VideoListItem>>
    val videoList: LiveData<PagingData<VideoListItem>> get() = _videoList
}