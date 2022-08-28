package com.clone.youtube.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.clone.youtube.model.MainVideoList
import com.clone.youtube.model.MainVideoListItem
import com.clone.youtube.model.MainVideoListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val mainVideoListRepository: MainVideoListRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    val mainVideoList = MutableLiveData<List<MainVideoListItem>>()

    fun getVideos() {
        val response = mainVideoListRepository.getVideos()
        response.enqueue(object : Callback<List<MainVideoListItem>> {
            override fun onResponse(call: Call<List<MainVideoListItem>>, response: Response<List<MainVideoListItem>>) {
                mainVideoList.postValue(response.body())
            }
            override fun onFailure(call: Call<List<MainVideoListItem>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}