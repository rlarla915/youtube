package com.clone.youtube.ui.play

import android.annotation.SuppressLint
import android.app.Application
import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.getSystemService
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clone.youtube.model.MainVideoListItem
import com.clone.youtube.model.PlayerVideoInfo
import com.clone.youtube.model.VideoRepository
import com.clone.youtube.model.offline.OfflineVideo
import com.clone.youtube.model.offline.OfflineVideoRepository
import com.clone.youtube.ui.play.MediaPlayer.MediaPlayerImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.io.File
import java.sql.Timestamp
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class PlayViewModel @Inject constructor(application: Application, private val videoRepository: VideoRepository, private val offlineVideoRepository: OfflineVideoRepository) : AndroidViewModel(application) {
    // 여기서 repository를 두개를 inject받는게 맞는가?
    private val context = getApplication<Application>().applicationContext
    val playerVideoInfo = MutableLiveData<PlayerVideoInfo>()
    val playerVideoInfoFromList = MutableLiveData<MainVideoListItem>()
    val playerVideoList = MutableLiveData<ArrayList<MainVideoListItem>>()
    val playWhenReady = MutableLiveData<Boolean>(true)
    val currentWindow = MutableLiveData<Int>(0)
    val playBackPosition = MutableLiveData<Long>(0L)

    lateinit var fileVideo: File
    lateinit var fileThumbnail: File
    val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    var downloadId : Long? = null

    private val mediaPlayer = MediaPlayerImpl()

    fun getMediaPlayer() = mediaPlayer

    fun play() {
        mediaPlayer.play(playerVideoInfoFromList.value?.videoUrl!!, playWhenReady.value!!, currentWindow.value!!, playBackPosition.value!!)

    }

    fun release() = mediaPlayer.releasePlayer(playWhenReady, currentWindow, playBackPosition)

    /*
    fun createVideoUrl(video: ApiVideo) =
        "https://res.cloudinary.com/demo/video/${video.type}/v${video.version}/${video.publicId}.${video.format}"

     */

    fun loadPlayerVideoInfo(){
        viewModelScope.launch {
            listOf(
                async { videoRepository.getVideoInfo(playerVideoInfo) },
                async { videoRepository.getVideoList(playerVideoList) }
            )
        }
    }

    fun clickCommentBox(){
        var mainActivity = context as AppCompatActivity
        var bottomSheetDialogComments = BottomSheetDialogComments()
        bottomSheetDialogComments.show(mainActivity.supportFragmentManager, "comments")
    }

    // offline storage
    @RequiresApi(Build.VERSION_CODES.O)
    fun setOfflineVideo(){
        viewModelScope.launch {
            downloadFromUrl("https://file-examples.com/wp-content/uploads/2017/04/file_example_MP4_1280_10MG.mp4")
            val offlineVideo : OfflineVideo = OfflineVideo(
                key = playerVideoInfoFromList.value?.id!!,
                videoUrl = Uri.fromFile(fileVideo).toString(),
                thumbnailUrl = Uri.fromFile(fileThumbnail).toString(),
                title = playerVideoInfoFromList.value?.title!!,
                createTime = Timestamp.valueOf(playerVideoInfoFromList.value?.createTime!!.format(
                    DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"))).time,
                view = playerVideoInfoFromList.value?.view!!,
                channelName = playerVideoInfoFromList.value?.channel?.name!!,
                channelProfileUrl = playerVideoInfoFromList.value?.channel?.profileUrl!!)
            offlineVideoRepository.setOfflineVideo(offlineVideo)
        }
    }
    fun downloadFromUrl(url : String){
        fileVideo = File(context.getExternalFilesDir(null), "${playerVideoInfoFromList.value?.id}.mp4")

        val requestVideo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            DownloadManager.Request(Uri.parse(url))
                .setTitle("Downloading a video")
                .setDescription("Download Offline Video")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setDestinationUri(Uri.fromFile(fileVideo))
                .setRequiresCharging(false)
                .setAllowedOverMetered(true)
                .setAllowedOverRoaming(true)
        } else {
            TODO("VERSION.SDK_INT < N")
        }

        fileThumbnail = File(context.getExternalFilesDir(null), "${playerVideoInfoFromList.value?.id}.jpg")

        val requestThumbnail = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            DownloadManager.Request(Uri.parse(playerVideoInfoFromList.value?.thumbnailUrl))
                .setTitle("Downloading a thumbnail")
                .setDescription("Download Offline Video Thumbnail")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
                .setDestinationUri(Uri.fromFile(fileThumbnail))
                .setRequiresCharging(false)
                .setAllowedOverMetered(true)
                .setAllowedOverRoaming(true)
        } else {
            TODO("VERSION.SDK_INT < N")
        }
        downloadManager.enqueue(requestThumbnail)
        downloadId = downloadManager.enqueue(requestVideo)
    }



}