package com.clone.youtube.ui.upload

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.clone.youtube.MainActivity
import com.clone.youtube.R
import com.clone.youtube.adapters.CommentListAdapter
import com.clone.youtube.adapters.MainVideoListAdapter
import com.clone.youtube.databinding.BottomSheetDialogCommentsBinding
import com.clone.youtube.databinding.BottomSheetDialogUploadBinding
import com.clone.youtube.model.Channel
import com.clone.youtube.model.Comment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime

@AndroidEntryPoint
class BottomSheetDialogUpload : BottomSheetDialogFragment() {
    lateinit var binding : BottomSheetDialogUploadBinding
    lateinit var mainActivity : MainActivity
    val bottomSheetDialogUploadViewModel : BottomSheetDialogUploadViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_dialog_upload, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = bottomSheetDialogUploadViewModel
        mainActivity = activity as MainActivity
        binding.bottomDialogClose.setOnClickListener {
            this.dismiss()
        }
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservable()

        /*
        var bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheetDialogComments)
        bottomSheetBehavior!!.state = BottomSheetBehavior.STATE_HIDDEN
        bottomSheetBehavior!!.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState){
                    BottomSheetBehavior.STATE_HIDDEN -> {
                        mainActivity.supportFragmentManager.popBackStack()
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                TODO("Not yet implemented")
            }

        })
         */

    }
    fun initObservable(){
        bottomSheetDialogUploadViewModel.clickUploadVideoCheck.observe(viewLifecycleOwner, Observer {
            val intent = Intent(mainActivity, UploadActivity::class.java)
            startActivity(intent)
        })
    }
/*
    override fun callUploadActivity() {
        val intent = Intent(mainActivity, UploadActivity::class.java)
        startActivity(intent)
    }

 */
}