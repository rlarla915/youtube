package com.clone.youtube.ui.upload

import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Size
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.clone.youtube.R
import com.clone.youtube.databinding.ActivityUploadBinding
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.get
import java.nio.ByteBuffer
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.Paths.get


@AndroidEntryPoint
class UploadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUploadBinding
    val uploadViewModel: UploadViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_upload)
        binding.viewModel = uploadViewModel
        binding.lifecycleOwner = this

        initObserve()

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

    fun initObserve(){
        uploadViewModel.checkUploadSuccess.observe(this, Observer {
            Toast.makeText(this, "Upload Success", Toast.LENGTH_SHORT).show()
        })
    }
}