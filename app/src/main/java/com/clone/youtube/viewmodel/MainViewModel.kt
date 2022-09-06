package com.clone.youtube.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel(){

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