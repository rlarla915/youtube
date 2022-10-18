package com.clone.youtube.ui.home

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

    val mainVideoList : MutableLiveData<PagingData<VideoListItem>> = videoRepository.getVideoList("").cachedIn(viewModelScope) as MutableLiveData<PagingData<VideoListItem>>

    fun loadMainVideoList() {


        /*
        viewModelScope.launch {
            videoRepository.getVideoList(mainVideoList)
        }

         */
        /* // 여러개의 api를 비동기적으로 처리하기 위해서 coroutines 사용.
        coroutineScope {
            listOf(
                async { videoRepository.getVideoList2() },
                async { videoRepository.getVideoList3() },
                async { videoRepository.getVideoList4() }
            )
        }.awaitAll() // 이거 observable로 짜면 await 안 걸어도 되겠지?

         */
    }
}