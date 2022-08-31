package com.clone.youtube.model

import androidx.lifecycle.MutableLiveData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainVideoListRepository @Inject constructor(private val mainVideoListServiceInstance: MainVideoListServiceInstance) {
        // 여기다 query문 집어 넣을 수 있음
        fun getVideoList(liveDataList : MutableLiveData<List<MainVideoListItem>>) {
                val call = mainVideoListServiceInstance.getVideoList()
                call.enqueue(object : Callback<List<MainVideoListItem>> {
                        override fun onResponse(call: Call<List<MainVideoListItem>>, response: Response<List<MainVideoListItem>>) {
                                liveDataList.postValue(response.body())
                        }
                        override fun onFailure(call: Call<List<MainVideoListItem>>, t: Throwable) {
                                TODO("Not yet implemented")
                        }
                })
        }
}
