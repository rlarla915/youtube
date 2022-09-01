package com.clone.youtube.ui.play

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.clone.youtube.MainActivity
import com.clone.youtube.R
import com.clone.youtube.adapters.VideoPlayerListAdapter
import com.clone.youtube.databinding.FragmentPlayBinding
import com.clone.youtube.databinding.VideoControllerBinding
import com.clone.youtube.model.Channel
import com.clone.youtube.model.Comment
import com.clone.youtube.model.MainVideoListItem
import com.clone.youtube.viewmodel.HomeViewModel
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.video_controller.view.*
import java.time.LocalDateTime

class PlayFragment : Fragment() {

    // This property is only valid between onCreateView and
    // onDestroyView.
    lateinit var mainActivity: MainActivity
    private lateinit var binding: FragmentPlayBinding
    private lateinit var controllerBinding: VideoControllerBinding
    val homeViewModel : HomeViewModel by viewModels()
    var videoInfo : MainVideoListItem? = null
    private var player : ExoPlayer? = null

    // need to move to viewmodel livadata
    private var PlayWhenReady : Boolean = true
    private var currentWindow : Int = 0
    private var playBackPosition : Long = 0L

    var fullscreen : Boolean = false


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mainActivity = activity as MainActivity
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_play, container, false)
        binding.lifecycleOwner = this

        videoInfo  = arguments?.getParcelable<MainVideoListItem>("videoInfo")


        binding.videoPlayer.exo_fullscreen.setOnClickListener {
            changeFullScreen(it)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var mainVideoDataList : ArrayList<MainVideoListItem> = arrayListOf()

        binding.recyclerVideoPlayer.layoutManager = LinearLayoutManager(mainActivity)
        binding.recyclerVideoPlayer.adapter = VideoPlayerListAdapter(videoInfo!!, mainVideoDataList)


    }

    override fun onPause() {
        super.onPause()
        if (Util.SDK_INT < 24){
            releasePlayer()
        }
    }

    override fun onStop() {
        super.onStop()
        if(Util.SDK_INT >= 24){
            releasePlayer()
        }
    }

    override fun onStart() {
        super.onStart()
        if (Util.SDK_INT >= 24){
            initPlayer()
        }
    }

    override fun onResume() {
        super.onResume()
        //hideSystemUi()
        if ((Util.SDK_INT < 24 || player == null)){
            initPlayer()
        }
    }

    // need to fix to make adaptive streaming format
    // https://developer.android.com/codelabs/exoplayer-intro#4
    private fun initPlayer(){
        player = ExoPlayer.Builder(mainActivity).build().also{
            exoPlayer ->
            binding.videoPlayer.player = exoPlayer
            val mediaItem = MediaItem.fromUri("http://sample.vodobox.net/skate_phantom_flex_4k/skate_phantom_flex_4k.m3u8")
            exoPlayer.setMediaItem(mediaItem)
            exoPlayer.playWhenReady = PlayWhenReady
            exoPlayer.seekTo(currentWindow, playBackPosition)
            exoPlayer.prepare()

        }

    }

    private fun releasePlayer() {
        player?.run {
            playBackPosition = this.currentPosition
            currentWindow = this.currentMediaItemIndex
            PlayWhenReady = this.playWhenReady
            release()
        }
        player = null
    }

    private fun hideSystemUi() {
        WindowCompat.setDecorFitsSystemWindows(mainActivity.window, false)
        WindowInsetsControllerCompat(mainActivity.window, binding.videoPlayer).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    private fun showSystemUi() {
        WindowCompat.setDecorFitsSystemWindows(mainActivity.window, true)
        WindowInsetsControllerCompat(mainActivity.window, binding.videoPlayer).let { controller ->
            controller.show(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_BARS_BY_SWIPE
        }
    }

    private fun changeFullScreen(view: View) {
        if (fullscreen){
            if (mainActivity.supportActionBar != null){
                mainActivity.supportActionBar!!.show()
            }
            mainActivity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            showSystemUi()
            mainActivity.nav_view.visibility = View.VISIBLE
            fullscreen = false
        }
        else {
            if (mainActivity.supportActionBar != null){
                mainActivity.supportActionBar!!.hide()
            }
            mainActivity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            hideSystemUi()
            mainActivity.nav_view.visibility = View.GONE
            fullscreen = true
        }
    }



}