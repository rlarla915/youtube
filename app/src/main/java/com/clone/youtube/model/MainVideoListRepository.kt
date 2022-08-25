package com.clone.youtube.model

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MainVideoListRepository {
    constructor(){

    }
    constructor(mainVideoListService: MainVideoListService) {
        @Singleton
        @Provides
        fun getVideos() = mainVideoListService.getVideos()
    }
}