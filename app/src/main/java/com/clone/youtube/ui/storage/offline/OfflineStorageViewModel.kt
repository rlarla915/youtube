package com.clone.youtube.ui.storage.offline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.clone.youtube.model.offline.OfflineVideo
import com.clone.youtube.model.offline.OfflineVideoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class OfflineStorageViewModel @Inject constructor(private val offlineVideoRepository: OfflineVideoRepository) :
    ViewModel() {

    fun getOfflineVideo() : Flow<PagingData<OfflineVideo>> {
        return offlineVideoRepository.getOfflineVideo("").cachedIn(viewModelScope)
    }
}
