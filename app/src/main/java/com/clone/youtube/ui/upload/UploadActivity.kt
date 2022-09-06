package com.clone.youtube.ui.upload

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.Size
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.clone.youtube.R
import com.clone.youtube.databinding.ActivityUploadBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UploadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUploadBinding
    val uploadViewModel: UploadViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_upload)
        binding.viewModel = uploadViewModel
        binding.lifecycleOwner = this

        val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()){ uri ->
            val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                contentResolver.loadThumbnail(uri!!, Size(400, 250), null)
            } else {
                TODO("VERSION.SDK_INT < Q")
            }
            uploadViewModel.thumbnail.postValue(bitmap)
            val stream = contentResolver.openInputStream(uri)
            uploadViewModel.uploadVideo(stream!!)
        }
        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.SingleMimeType("video/mp4")))
    }
}