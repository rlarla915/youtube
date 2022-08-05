package com.clone.youtube.ui.play

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.clone.youtube.MainActivity
import com.clone.youtube.R
import com.clone.youtube.adapters.CommentListAdapter
import com.clone.youtube.adapters.MainVideoListAdapter
import com.clone.youtube.databinding.BottomSheetDialogCommentsBinding
import com.clone.youtube.model.Channel
import com.clone.youtube.model.Comment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.time.LocalDateTime

class BottomSheetDialogComments : BottomSheetDialogFragment() {
    lateinit var binding : BottomSheetDialogCommentsBinding
    lateinit var mainActivity : MainActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_dialog_comments, container, false)
        binding.lifecycleOwner = this
        mainActivity = activity as MainActivity
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var testComments : ArrayList<Comment> = arrayListOf()
        testComments.add(Comment(Channel("침착맨", R.drawable.sample_profile, 150000), LocalDateTime.of(2022, 1, 26, 19, 30, 20), 15000, "테스트 댓글"))
        testComments.add(Comment(Channel("침착맨", R.drawable.sample_profile, 150000), LocalDateTime.of(2022, 1, 26, 19, 30, 20), 15000, "테스트 댓글"))
        testComments.add(Comment(Channel("침착맨", R.drawable.sample_profile, 150000), LocalDateTime.of(2022, 1, 26, 19, 30, 20), 15000, "테스트 댓글"))
        testComments.add(Comment(Channel("침착맨", R.drawable.sample_profile, 150000), LocalDateTime.of(2022, 1, 26, 19, 30, 20), 15000, "테스트 댓글"))

        binding.recyclerComments.layoutManager = LinearLayoutManager(mainActivity)
        binding.recyclerComments.adapter = CommentListAdapter(testComments)
    }
}