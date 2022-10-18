package com.clone.youtube.repository.video

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.*
import com.clone.youtube.model.PlayerVideoInfo
import com.clone.youtube.model.VideoListItem
import com.clone.youtube.repository.LocalJsonServer
import retrofit2.*
import javax.inject.Inject

class VideoRepository @Inject constructor(@LocalJsonServer retrofit: Retrofit) {
    private val client = retrofit.create(VideoApiService::class.java)
    fun getVideoList(query: String): LiveData<PagingData<VideoListItem>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 10),
            pagingSourceFactory = { VideoPagingSource(client, query) }
        ).liveData
    }

    suspend fun getVideoInfo(liveDataVideoInfo: MutableLiveData<PlayerVideoInfo>) {
        val response = client.getVideoInfo()
        if (response.isSuccessful && response.body() != null) {
            liveDataVideoInfo.postValue(response.body())
        }
    }

    suspend fun getUnderVideoList(liveDataList: MutableLiveData<ArrayList<VideoListItem>>) {
        val response = client.getVideoList()
        if (response.isSuccessful && response.body() != null) {
            liveDataList.postValue(ArrayList(response.body()))
        }
    }
}
