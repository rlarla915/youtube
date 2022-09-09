package com.clone.youtube.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.clone.youtube.repository.GoogleCloudStorage
import com.clone.youtube.repository.upload.UploadApiService
import com.clone.youtube.repository.video.VideoApiService
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.*
import java.io.InputStream
import javax.inject.Inject


// domain마다 분리하면 좋음. 개발 안 된 서버를 나눠서 테스트 할 수 있음.
// video, channel,
// backend for frontend(bff) 앱은 서버 하나만 바라보고. 서버가 알아서 해줌.
class UploadRepository @Inject constructor(@GoogleCloudStorage retrofit: Retrofit) {
        private val client = retrofit.create(UploadApiService::class.java)

        suspend fun uploadVideo(inputStream: InputStream, checkUploadSuccess : MutableLiveData<Unit>){
                val body = RequestBody.create(MediaType.parse("application/octet"), inputStream.readBytes())
                val response = client.uploadVideo(body)
                //Log.d("network", response?.code().toString())
                //Log.d("network", response?.isSuccessful.toString())
                if (response != null && response.isSuccessful){
                        checkUploadSuccess.postValue(Unit)
                }
        }
}
