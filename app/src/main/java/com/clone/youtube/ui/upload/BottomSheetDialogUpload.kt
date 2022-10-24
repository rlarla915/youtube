package com.clone.youtube.ui.upload

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.clone.youtube.ui.main.MainActivity
import com.clone.youtube.R
import com.clone.youtube.databinding.BottomSheetDialogUploadBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomSheetDialogUpload : BottomSheetDialogFragment() {
    private lateinit var _binding: BottomSheetDialogUploadBinding
    private val binding get() = _binding
    private val mainActivity: MainActivity by lazy { activity as MainActivity }
    private val bottomSheetDialogUploadViewModel: BottomSheetDialogUploadViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_dialog_upload, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = bottomSheetDialogUploadViewModel
        binding.bottomDialogClose.setOnClickListener {
            this.dismiss()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservable()
    }

    private fun initObservable() {
        bottomSheetDialogUploadViewModel.clickUploadVideoCheck.observe(
            viewLifecycleOwner,
            Observer {
                val intent = Intent(mainActivity, UploadActivity::class.java)
                startActivity(intent)
            })
    }
}