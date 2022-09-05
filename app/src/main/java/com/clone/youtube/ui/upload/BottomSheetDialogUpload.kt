package com.clone.youtube.ui.upload

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.clone.youtube.MainActivity
import com.clone.youtube.R
import com.clone.youtube.adapters.CommentListAdapter
import com.clone.youtube.adapters.MainVideoListAdapter
import com.clone.youtube.databinding.BottomSheetDialogCommentsBinding
import com.clone.youtube.model.Channel
import com.clone.youtube.model.Comment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.time.LocalDateTime

class BottomSheetDialogUpload : BottomSheetDialogFragment() {
    lateinit var binding : BottomSheetDialogCommentsBinding
    lateinit var mainActivity : MainActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_dialog_comments, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        mainActivity = activity as MainActivity
        binding.bottomDialogClose.setOnClickListener {
            this.dismiss()
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        dialog?.setCanceledOnTouchOutside(false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


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


        var testComments : ArrayList<Comment> = arrayListOf()
        testComments.add(Comment(Channel("침착맨", "https://picsum.photos/600/600/?random", 150000), LocalDateTime.of(2022, 1, 26, 19, 30, 20), 15000, "테스트 댓글"))
        testComments.add(Comment(Channel("침착맨", "https://picsum.photos/600/600/?random", 150000), LocalDateTime.of(2022, 1, 26, 19, 30, 20), 15000, "테스트 댓글"))
        testComments.add(Comment(Channel("침착맨", "https://picsum.photos/600/600/?random", 150000), LocalDateTime.of(2022, 1, 26, 19, 30, 20), 15000, "테스트 댓글"))
        testComments.add(Comment(Channel("침착맨", "https://picsum.photos/600/600/?random", 150000), LocalDateTime.of(2022, 1, 26, 19, 30, 20), 15000, "테스트 댓글"))

        binding.recyclerComments.layoutManager = LinearLayoutManager(mainActivity)
        binding.recyclerComments.adapter = CommentListAdapter(testComments)
    }
}