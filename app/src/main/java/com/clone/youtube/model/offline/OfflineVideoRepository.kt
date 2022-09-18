package com.clone.youtube.model.offline

import androidx.lifecycle.MutableLiveData
import javax.inject.Inject
import javax.inject.Singleton

class OfflineVideoRepository @Inject constructor(private val offlineVideoDao: OfflineVideoDao) {

    suspend fun getOfflineVideo(liveData: MutableLiveData<List<OfflineVideo>>){
        liveData.postValue(offlineVideoDao.getAllVideo())
    }

    suspend fun setOfflineVideo(offlineVideo: OfflineVideo){
        offlineVideoDao.insertAll(offlineVideo)
    }
}