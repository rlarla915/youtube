package com.clone.youtube.ui.storage

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.clone.youtube.extensions.Event
import com.clone.youtube.model.offline.OfflineVideoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import javax.inject.Inject

@HiltViewModel
class StorageViewModel @Inject constructor(private val offlineVideoRepository: OfflineVideoRepository) :
    ViewModel() {
    val checkNavigateToOfflineStorage = MutableLiveData<Event<String>>()

    fun navigateToOfflineStorage(){
        checkNavigateToOfflineStorage.value = Event("offline_storage")
    }
}
