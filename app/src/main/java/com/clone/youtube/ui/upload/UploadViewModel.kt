package com.clone.youtube.ui.upload

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.clone.youtube.MainActivity
import com.clone.youtube.ui.play.BottomSheetDialogComments
import com.clone.youtube.ui.upload.BottomSheetDialogUpload
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UploadViewModel @Inject constructor() : ViewModel(){

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