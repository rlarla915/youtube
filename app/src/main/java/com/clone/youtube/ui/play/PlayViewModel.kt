package com.clone.youtube.ui.play

import android.app.Application
import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.clone.youtube.extensions.Event
import com.clone.youtube.model.VideoListItem
import com.clone.youtube.model.PlayerVideoInfo
import com.clone.youtube.model.offline.OfflineVideo
import com.clone.youtube.model.offline.OfflineVideoRepository
import com.clone.youtube.repository.video.VideoRepository
import com.clone.youtube.ui.play.mediaplayer.MediaPlayerImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.io.File
import java.sql.Timestamp
import org.threeten.bp.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class PlayViewModel @Inject constructor(
    application: Application,
    private val videoRepository: VideoRepository,
    private val offlineVideoRepository: OfflineVideoRepository
) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    private val _playerVideoInfo = MutableLiveData<PlayerVideoInfo>()
    val playerVideoInfo: LiveData<PlayerVideoInfo> get() = _playerVideoInfo
    private val _playerVideoInfoFromList = MutableLiveData<VideoListItem>()
    val playerVideoInfoFromList: LiveData<VideoListItem> get() = _playerVideoInfoFromList
    private val _playerVideoList = MutableLiveData<ArrayList<VideoListItem>>()
    val playerVideoList: LiveData<ArrayList<VideoListItem>> get() = _playerVideoList
    private val _playWhenReady = MutableLiveData<Boolean>(true)
    private val playWhenReady: LiveData<Boolean> get() = _playWhenReady
    private val _currentWindow = MutableLiveData<Int>(0)
    private val currentWindow: LiveData<Int> get() = _currentWindow
    private val _playBackPosition = MutableLiveData<Long>(0L)
    private val playBackPosition: LiveData<Long> get() = _playBackPosition

    private val _clickComments = MutableLiveData<Event<String>>()
    val clickComments: LiveData<Event<String>> get() = _clickComments

    private lateinit var fileVideo: File
    private lateinit var fileThumbnail: File
    private val downloadManager =
        context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    private var downloadId: Long? = null

    private val mediaPlayer = MediaPlayerImpl()

    fun getMediaPlayer() = mediaPlayer

    fun play() {
        mediaPlayer.play(
            playerVideoInfoFromList.value?.videoUrl!!,
            playWhenReady.value!!,
            currentWindow.value!!,
            playBackPosition.value!!
        )

    }

    fun release() = mediaPlayer.releasePlayer(_playWhenReady, _currentWindow, _playBackPosition)

    fun loadPlayerVideoInfo() {
        viewModelScope.launch {
            listOf(
                async { videoRepository.getVideoInfo(_playerVideoInfo) },
                async { videoRepository.getUnderVideoList(_playerVideoList) }
            )
        }
    }

    fun clickCommentsBox() {
        _clickComments.value = Event("comments")
    }

    // offline storage
    fun setOfflineVideo() {
        viewModelScope.launch {
            downloadFromUrl("https://file-examples.com/wp-content/uploads/2017/04/file_example_MP4_1280_10MG.mp4")
            val offlineVideo: OfflineVideo = OfflineVideo(
                key = playerVideoInfoFromList.value?.id!!,
                videoUrl = Uri.fromFile(fileVideo).toString(),
                thumbnailUrl = Uri.fromFile(fileThumbnail).toString(),
                title = playerVideoInfoFromList.value?.title!!,
                createTime = Timestamp.valueOf(
                    playerVideoInfoFromList.value?.createTime!!.format(
                        DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")
                    )
                ).time,
                view = playerVideoInfoFromList.value?.view!!,
                channelName = playerVideoInfoFromList.value?.channel?.name!!,
                channelProfileUrl = playerVideoInfoFromList.value?.channel?.profileUrl!!
            )
            offlineVideoRepository.setOfflineVideo(offlineVideo)
        }
    }

    fun setPlayerVideoInfoFromList(videoListItem: VideoListItem) {
        _playerVideoInfoFromList.value = videoListItem
    }

    private fun downloadFromUrl(url: String) {
        fileVideo =
            File(context.getExternalFilesDir(null), "${playerVideoInfoFromList.value?.id}.mp4")

        val requestVideo = DownloadManager.Request(Uri.parse(url))
            .setTitle("Downloading a video")
            .setDescription("Download Offline Video")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationUri(Uri.fromFile(fileVideo))
            .setRequiresCharging(false)
            .setAllowedOverMetered(true)
            .setAllowedOverRoaming(true)


        fileThumbnail =
            File(context.getExternalFilesDir(null), "${playerVideoInfoFromList.value?.id}.jpg")

        val requestThumbnail =
            DownloadManager.Request(Uri.parse(playerVideoInfoFromList.value?.thumbnailUrl))
                .setTitle("Downloading a thumbnail")
                .setDescription("Download Offline Video Thumbnail")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
                .setDestinationUri(Uri.fromFile(fileThumbnail))
                .setRequiresCharging(false)
                .setAllowedOverMetered(true)
                .setAllowedOverRoaming(true)

        downloadManager.enqueue(requestThumbnail)
        downloadId = downloadManager.enqueue(requestVideo)
    }

}