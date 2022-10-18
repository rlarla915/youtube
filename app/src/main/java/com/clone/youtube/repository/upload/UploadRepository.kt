package com.clone.youtube.repository.upload

import androidx.lifecycle.MutableLiveData
import com.clone.youtube.extensions.Event
import com.clone.youtube.repository.GoogleCloudStorage
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.*
import java.io.InputStream
import javax.inject.Inject

class UploadRepository @Inject constructor(@GoogleCloudStorage retrofit: Retrofit) {
    private val client = retrofit.create(UploadApiService::class.java)

    suspend fun uploadVideo(
        inputStream: InputStream,
        checkUploadSuccess: MutableLiveData<Event<String>>
    ) {
        val body = RequestBody.create(MediaType.parse("application/octet"), inputStream.readBytes())
        val response = client.uploadVideo(body)
        if (response != null && response.isSuccessful) {
            checkUploadSuccess.postValue(Event("upload"))
        }
    }
}
