package com.clone.youtube.model

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import javax.inject.Singleton


interface MainVideoListServiceInstance {
    @GET("videos")
    fun getVideoList(): Call<List<MainVideoListItem>>
}