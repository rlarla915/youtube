package com.clone.youtube.ui.play

import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.clone.youtube.MainActivity
import com.clone.youtube.R
import com.clone.youtube.adapters.MainVideoListAdapter
import com.clone.youtube.adapters.UnderVideoListAdapter
import com.clone.youtube.adapters.VideoPlayerListAdapter
import com.clone.youtube.databinding.FragmentPlayBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.util.Util
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.video_controller.view.*

@AndroidEntryPoint
class PlayFragment : Fragment() {

    // This property is only valid between onCreateView and
    // onDestroyView.
    lateinit var mainActivity: MainActivity
    private lateinit var binding: FragmentPlayBinding
    //private lateinit var controllerBinding: VideoControllerBinding
    val playViewModel : PlayViewModel by viewModels()
    private var player : ExoPlayer? = null

    var fullscreen : Boolean = false

    val args : PlayFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mainActivity = activity as MainActivity
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_play, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = playViewModel

        playViewModel.playerVideoInfoFromList.value = args.videoInfoFromList

        initCommentsClick()
        binding.videoPlayer.exo_fullscreen.setOnClickListener {
            changeFullScreen(it)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        playViewModel.loadPlayerVideoInfo()

        binding.recyclerVideoPlayer.layoutManager = LinearLayoutManager(mainActivity)
        binding.recyclerVideoPlayer.adapter = UnderVideoListAdapter() //VideoPlayerListAdapter(this, mainActivity)

    }

    /*
    @RequiresApi(Build.VERSION_CODES.O)
    override fun offlineStorage() {
        playViewModel.setOfflineVideo()
    }
     */

    override fun onPause() {
        super.onPause()
        if (Util.SDK_INT < 24){
            playViewModel.release()
        }
    }

    override fun onStop() {
        super.onStop()
        if(Util.SDK_INT >= 24){
            playViewModel.release()
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
        player = playViewModel.getMediaPlayer().getPlayer(mainActivity)
        binding.videoPlayer.player = player
        playViewModel.play()

    }

    private fun initCommentsClick(){
        playViewModel.clickComments.observe(viewLifecycleOwner, Observer {
            var bottomSheetDialogComments = BottomSheetDialogComments()
            bottomSheetDialogComments.show(mainActivity.supportFragmentManager, "comments")
        })
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