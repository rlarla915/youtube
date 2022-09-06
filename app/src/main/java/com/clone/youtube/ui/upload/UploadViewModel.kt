package com.clone.youtube.ui.upload

import android.app.Application
import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clone.youtube.MainActivity
import com.clone.youtube.model.UploadRepository
import com.clone.youtube.ui.play.BottomSheetDialogComments
import com.clone.youtube.ui.upload.BottomSheetDialogUpload
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.InputStream
import javax.inject.Inject

@HiltViewModel
class UploadViewModel @Inject constructor(private val uploadRepository: UploadRepository) : ViewModel(){
    val thumbnail = MutableLiveData<Bitmap>()

    fun uploadVideo(inputStream: InputStream){
        viewModelScope.launch {
            uploadRepository.uploadVideo(inputStream)
        }
    }




}
/*
class MainViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    fun fabUploadClick(){
        Log.d("XX", "clicked")
        var bottomSheetDialogUpload = BottomSheetDialogUpload()
        val mainActivity = context as MainActivity
        bottomSheetDialogUpload.show(mainActivity.supportFragmentManager, "comments")
    }
}

 */