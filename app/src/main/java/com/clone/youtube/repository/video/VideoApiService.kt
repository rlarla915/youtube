package com.clone.youtube.repository.video

import com.clone.youtube.model.MainVideoListItem
import com.clone.youtube.model.PlayerVideoInfo
import com.clone.youtube.model.PlayerVideoInfoDAO
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST


interface VideoApiService {
    @GET("videos")
    suspend fun getVideoList(): Response<ArrayList<MainVideoListItem>>

    @GET("video_info")
    suspend fun getVideoInfo(): Response<PlayerVideoInfo>
}