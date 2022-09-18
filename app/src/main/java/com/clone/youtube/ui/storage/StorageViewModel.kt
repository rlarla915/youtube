package com.clone.youtube.ui.storage

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clone.youtube.model.MainVideoListItem
import com.clone.youtube.model.VideoRepository
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

}
