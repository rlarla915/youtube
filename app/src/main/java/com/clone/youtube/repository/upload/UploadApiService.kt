package com.clone.youtube.repository.upload

import com.clone.youtube.model.MainVideoListItem
import com.clone.youtube.model.PlayerVideoInfo
import com.clone.youtube.model.PlayerVideoInfoDAO
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST


interface UploadApiService {
    @Headers("Content-Type: video/mp4")
    @POST("youtube_videos_bucket")
    suspend fun uploadVideo(@Body body: RequestBody)
}