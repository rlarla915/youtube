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

@Module
@InstallIn(SingletonComponent::class)
interface MainVideoListService {
    @GET("/api/video")
    fun getVideos(): Call<List<MainVideoListItem>>

    companion object {
        var mainVideoListService : MainVideoListService? = null


        @Singleton
        @Provides
        fun getInstance() : MainVideoListService {
            if (mainVideoListService == null){
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://172.24.246.96/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                mainVideoListService = retrofit.create(MainVideoListService::class.java)
            }
            return mainVideoListService!!
        }
    }
}