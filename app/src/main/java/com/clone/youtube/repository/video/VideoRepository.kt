package com.clone.youtube.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.clone.youtube.repository.video.VideoApiService
import retrofit2.*
import javax.inject.Inject


// domain마다 분리하면 좋음. 개발 안 된 서버를 나눠서 테스트 할 수 있음.
// video, channel,
// backend for frontend(bff) 앱은 서버 하나만 바라보고. 서버가 알아서 해줌.
class VideoRepository @Inject constructor(retrofit: Retrofit) {
        private val client = retrofit.create(VideoApiService::class.java)

        // 여기다 query문 집어 넣을 수 있음
        // suspend 방식은 사용하는 직접적인 객체를 return 해야함
        // 코루틴 방식으로 짜는게 Callback 지옥에서 벗어날 수 있음. -> 이거 면접에서 말하면 될 듯.
        suspend fun getVideoList(liveDataList : MutableLiveData<ArrayList<MainVideoListItem>>) {
                val response = client.getVideoList()
                if (response.isSuccessful && response.body() != null){
                        liveDataList.postValue(response.body())
                }
        }

        suspend fun getVideoInfo(liveDataVideoInfo : MutableLiveData<PlayerVideoInfo>){
                val response = client.getVideoInfo()
                if (response.isSuccessful && response.body() != null){
                        liveDataVideoInfo.postValue(response.body())
                }
        }
}
