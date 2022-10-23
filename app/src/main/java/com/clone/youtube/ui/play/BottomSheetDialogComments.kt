package com.clone.youtube.ui.play

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.clone.youtube.ui.main.MainActivity
import com.clone.youtube.R
import com.clone.youtube.adapters.CommentListAdapter
import com.clone.youtube.databinding.BottomSheetDialogCommentsBinding
import com.clone.youtube.model.Channel
import com.clone.youtube.model.Comment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.threeten.bp.LocalDateTime

class BottomSheetDialogComments : BottomSheetDialogFragment() {
    private lateinit var _binding: BottomSheetDialogCommentsBinding
    private val binding get() = _binding
    private val mainActivity: MainActivity by lazy { activity as MainActivity }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.bottom_sheet_dialog_comments,
            container,
            false
        )
        binding.lifecycleOwner = viewLifecycleOwner
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

        val testComments: ArrayList<Comment> = arrayListOf()
        testComments.add(
            Comment(
                Channel("침착맨", "https://picsum.photos/600/600/?random", 150000),
                LocalDateTime.of(2022, 1, 26, 19, 30, 20),
                15000,
                "테스트 댓글"
            )
        )
        testComments.add(
            Comment(
                Channel("침착맨", "https://picsum.photos/600/600/?random", 150000),
                LocalDateTime.of(2022, 1, 26, 19, 30, 20),
                15000,
                "테스트 댓글"
            )
        )
        testComments.add(
            Comment(
                Channel("침착맨", "https://picsum.photos/600/600/?random", 150000),
                LocalDateTime.of(2022, 1, 26, 19, 30, 20),
                15000,
                "테스트 댓글"
            )
        )
        testComments.add(
            Comment(
                Channel("침착맨", "https://picsum.photos/600/600/?random", 150000),
                LocalDateTime.of(2022, 1, 26, 19, 30, 20),
                15000,
                "테스트 댓글"
            )
        )

        binding.recyclerComments.layoutManager = LinearLayoutManager(mainActivity)
        binding.recyclerComments.adapter = CommentListAdapter(testComments)
    }
}