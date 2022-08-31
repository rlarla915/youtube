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
object MainVideoListModule {

    @Singleton
    @Provides
    fun getMainVideoListServiceInstance(retrofit: Retrofit): MainVideoListServiceInstance {
        return retrofit.create(MainVideoListServiceInstance::class.java)
    }

    @Singleton
    @Provides
    fun getMainVideoListInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://localhost:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}