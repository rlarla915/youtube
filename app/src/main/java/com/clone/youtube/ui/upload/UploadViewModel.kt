package com.clone.youtube.ui.upload

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clone.youtube.extensions.Event
import com.clone.youtube.repository.upload.UploadRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.InputStream
import javax.inject.Inject

@HiltViewModel
class UploadViewModel @Inject constructor(private val uploadRepository: UploadRepository) :
    ViewModel() {
    private val _thumbnail = MutableLiveData<Bitmap>()
    val thumbnail : LiveData<Bitmap> get() = _thumbnail
    val checkUploadSuccess = MutableLiveData<Event<String>>()
    val checkClose = MutableLiveData<Event<String>>()
    lateinit var inputStream: InputStream

    fun uploadVideo() {
        viewModelScope.launch {
            uploadRepository.uploadVideo(inputStream, checkUploadSuccess)
            checkClose.postValue(Event("close"))
        }
    }

    fun close() {
        checkClose.value = Event("close")
    }

    fun setThumbnail(bitmap: Bitmap){
        _thumbnail.postValue(bitmap)
    }
}