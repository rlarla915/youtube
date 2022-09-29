package com.clone.youtube.model.offline

import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

class OfflineVideoRepository @Inject constructor(private val offlineVideoDao: OfflineVideoDao) {

    fun getOfflineVideo(query : String) : Flow<PagingData<OfflineVideo>>{
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {  OfflinePagingSource(offlineVideoDao, query) }
        ).flow
    }

    suspend fun setOfflineVideo(offlineVideo: OfflineVideo){
        offlineVideoDao.insertAll(offlineVideo)
    }
}