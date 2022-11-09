package com.clone.youtube.ui.play

import android.app.PictureInPictureParams
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
//import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.clone.youtube.R
import com.clone.youtube.ui.main.MainActivity
import com.clone.youtube.adapters.UnderVideoListAdapter
import com.clone.youtube.databinding.FragmentPlayBinding
import com.google.android.exoplayer2.ExoPlayer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayFragment : Fragment() {
    private val mainActivity: MainActivity by lazy { activity as MainActivity }
    private lateinit var _binding: FragmentPlayBinding
    private val binding get() = _binding
    private val playViewModel: PlayViewModel by viewModels()
    private var player: ExoPlayer? = null
    private var fullscreen: Boolean = false
    private val args: PlayFragmentArgs by navArgs()
    private val fullScreenButton : ImageButton by lazy { binding.videoPlayer.findViewById<ImageButton>(R.id.exo_fullscreen) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlayBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = playViewModel
        playViewModel.setPlayerVideoInfoFromList(args.videoInfoFromList)
        binding.recyclerVideoPlayer.layoutManager = LinearLayoutManager(mainActivity)
        binding.recyclerVideoPlayer.adapter =
            UnderVideoListAdapter() // [fix] apply endless scroll or two viewholder paging adapter
        initObserve()

        fullScreenButton.setOnClickListener {
            changeFullScreen(it)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        playViewModel.loadPlayerVideoInfo()
    }

    override fun onStop() {
        super.onStop()
        playViewModel.release()
    }

    override fun onStart() {
        super.onStart()
        initPlayer()
    }

    override fun onResume() {
        super.onResume()
        hideSystemUi()
        if (player == null) {
            initPlayer()
        }
    }

    private fun initPlayer() {
        player = playViewModel.getMediaPlayer().getPlayer(mainActivity)
        binding.videoPlayer.player = player
        playViewModel.play()

    }

    private fun initObserve() {
        playViewModel.clickComments.observe(viewLifecycleOwner, Observer {
            val bottomSheetDialogComments = BottomSheetDialogComments()
            bottomSheetDialogComments.show(mainActivity.supportFragmentManager, "comments")
        })
    }

    private fun hideSystemUi() {
        WindowCompat.setDecorFitsSystemWindows(mainActivity.window, false)
        WindowInsetsControllerCompat(mainActivity.window, binding.videoPlayer).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
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
        val button = view as ImageButton
        if (fullscreen) {
            showSystemUi()
            if (mainActivity.supportActionBar != null) {
                mainActivity.supportActionBar!!.show()
            }
            mainActivity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
            mainActivity.binding.navView.visibility = View.VISIBLE
            mainActivity.binding.fabUpload.visibility = View.VISIBLE
            button.setImageResource(R.drawable.ic_fullscreen_24)
            fullscreen = false
        } else {
            hideSystemUi()
            if (mainActivity.supportActionBar != null) {
                mainActivity.supportActionBar!!.hide()
            }
            mainActivity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            mainActivity.binding.navView.visibility = View.GONE
            mainActivity.binding.fabUpload.visibility = View.GONE
            button.setImageResource(R.drawable.ic_fullscreen_exit_24)
            fullscreen = true
        }
    }
}