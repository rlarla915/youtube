package com.clone.youtube.model

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

class MainVideoListRepository @Inject constructor(private val mainVideoListService: MainVideoListService) {
        fun getVideos() = mainVideoListService.getVideos()
}
