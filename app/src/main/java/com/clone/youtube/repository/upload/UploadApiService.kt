package com.clone.youtube.repository.upload

import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface UploadApiService {
    @Headers("Content-Type: video/mp4")
    @PUT("youtube_videos_bucket/test.mp4")
    suspend fun uploadVideo(@Body body: RequestBody): Response<RequestBody>?
}