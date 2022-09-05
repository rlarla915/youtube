package com.clone.youtube.repository.video

import com.clone.youtube.model.MainVideoListItem
import com.clone.youtube.model.PlayerVideoInfo
import com.clone.youtube.model.PlayerVideoInfoDAO
import retrofit2.Response
import retrofit2.http.GET


interface VideoApiService {
    @GET("videos")
    suspend fun getVideoList(): Response<ArrayList<MainVideoListItem>>

    @GET("video_info")
    suspend fun getVideoInfo(): Response<PlayerVideoInfo>
}