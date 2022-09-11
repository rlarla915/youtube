package com.clone.youtube.model.offline

import androidx.lifecycle.MutableLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OfflineVideoRepository @Inject constructor(private val offlineVideoDao: OfflineVideoDao) {

    fun getOfflineVideo(liveData: MutableLiveData<ArrayList<OfflineVideo>>){
        liveData.postValue(offlineVideoDao.getAllVideo())
    }
}