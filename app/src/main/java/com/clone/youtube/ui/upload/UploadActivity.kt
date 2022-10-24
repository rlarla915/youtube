package com.clone.youtube.ui.upload

import android.os.Build
import android.os.Bundle
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

@AndroidEntryPoint
class UploadActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityUploadBinding
    private val binding get() = _binding
    private val uploadViewModel: UploadViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = DataBindingUtil.setContentView(this, R.layout.activity_upload)
        binding.viewModel = uploadViewModel
        binding.lifecycleOwner = this

        initObserve()

        val pickMedia =
            registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
                if (uri == null) {
                    finish()
                } else {
                    val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        contentResolver.loadThumbnail(uri!!, Size(400, 250), null)
                    } else {
                        TODO("VERSION.SDK_INT < Q")
                    }
                    uploadViewModel.setThumbnail(bitmap)
                    uploadViewModel.inputStream = contentResolver.openInputStream(uri)!!
                }
            }
        pickMedia.launch(
            PickVisualMediaRequest(
                ActivityResultContracts.PickVisualMedia.SingleMimeType(
                    "video/mp4"
                )
            )
        )
    }

    private fun initObserve() {
        uploadViewModel.checkUploadSuccess.observe(this, Observer {
            Toast.makeText(applicationContext, "Upload Success", Toast.LENGTH_SHORT).show()
        })
        uploadViewModel.checkClose.observe(this, Observer {
            finish()
        })
    }
}