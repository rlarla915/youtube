package com.clone.youtube.repository.video

import com.clone.youtube.model.VideoListItem
import com.clone.youtube.model.PlayerVideoInfo
import retrofit2.Response
import retrofit2.http.GET

interface VideoApiService {
    @GET("videos")
    suspend fun getVideoList(): Response<List<VideoListItem>> // [fix] : 나중에 서버에서 같이 주면 새로운 response dao(data class) 만들어서 page 번호까지 받아오기

    @GET("video_info")
    suspend fun getVideoInfo(): Response<PlayerVideoInfo>
}