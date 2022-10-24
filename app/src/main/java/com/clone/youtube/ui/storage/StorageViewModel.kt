package com.clone.youtube.ui.storage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.clone.youtube.extensions.Event
import com.clone.youtube.model.offline.OfflineVideoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StorageViewModel @Inject constructor(private val offlineVideoRepository: OfflineVideoRepository) :
    ViewModel() {
    val checkNavigateToOfflineStorage = MutableLiveData<Event<String>>()

    fun navigateToOfflineStorage() {
        checkNavigateToOfflineStorage.value = Event("offline_storage")
    }
}
