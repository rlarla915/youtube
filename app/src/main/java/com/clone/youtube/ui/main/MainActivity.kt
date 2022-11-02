package com.clone.youtube.ui.main

import android.app.PictureInPictureParams
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.clone.youtube.R
import com.clone.youtube.databinding.ActivityMainBinding
import com.clone.youtube.ui.upload.BottomSheetDialogUpload
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding
    val binding get() = _binding
    val navController by lazy { findNavController(R.id.nav_host_fragment_activity_main) }
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("QQ", "create")

        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = mainViewModel
        binding.lifecycleOwner = this

        val navView: BottomNavigationView = binding.navView
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_shorts,
                R.id.action_empty,
                R.id.navigation_subscribe,
                R.id.navigation_storage
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        binding.fabUpload.setOnClickListener {
            val bottomSheetDialogUpload = BottomSheetDialogUpload()
            bottomSheetDialogUpload.show(supportFragmentManager, "Upload")
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    fun enterPIPMode(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && packageManager.hasSystemFeature(
                PackageManager.FEATURE_PICTURE_IN_PICTURE) ){
            // need to update positions
            Log.d("QQ", "AAAAA")
            val params = PictureInPictureParams.Builder()
            enterPictureInPictureMode(params.build())
        }
        else {
            enterPictureInPictureMode()
        }
    }

    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        // [fix] need if phase to check player is on play
        enterPIPMode()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onPictureInPictureModeChanged(
        isInPictureInPictureMode: Boolean,
        newConfig: Configuration
    ) {
        if (isInPictureInPictureMode){

        }
        else {

        }


        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig)
    }

    override fun onStop() {
        super.onStop()
        if (packageManager.hasSystemFeature(
            PackageManager.FEATURE_PICTURE_IN_PICTURE)) {
            finishAndRemoveTask()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("QQ", "start")
    }

    override fun onResume() {
        super.onResume()
        Log.d("QQ", "resume")
    }
}