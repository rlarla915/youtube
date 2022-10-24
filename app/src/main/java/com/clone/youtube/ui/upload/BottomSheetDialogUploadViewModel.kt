package com.clone.youtube.ui.upload

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BottomSheetDialogUploadViewModel @Inject constructor() : ViewModel() {
    val clickUploadVideoCheck = MutableLiveData<Unit>()
    fun clickUploadVideo() {
        clickUploadVideoCheck.value = Unit
    }
}