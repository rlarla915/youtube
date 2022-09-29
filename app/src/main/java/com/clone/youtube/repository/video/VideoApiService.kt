package com.clone.youtube.repository.video

import androidx.paging.PagingData
import com.clone.youtube.model.MainVideoListItem
import com.clone.youtube.model.PlayerVideoInfo
import com.clone.youtube.model.PlayerVideoInfoDAO
import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST


interface VideoApiService {
    @GET("videos")
    suspend fun getVideoList(): Response<List<MainVideoListItem>> // [fix] 나중에 서버에서 같이 주면 새로운 response dao(data class) 만들어서 page 번호까지 받아오기

    @GET("video_info")
    suspend fun getVideoInfo(): Response<PlayerVideoInfo>
}