package com.clone.youtube.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.clone.youtube.model.MainVideoListItem
import com.clone.youtube.model.MainVideoListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val mainVideoListRepository: MainVideoListRepository) : ViewModel() {

    val mainVideoList = MutableLiveData<ArrayList<MainVideoListItem>>()

    fun loadMainVideoList(){
        mainVideoListRepository.getVideoList(mainVideoList)
    }
}