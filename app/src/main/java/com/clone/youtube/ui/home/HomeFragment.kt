package com.clone.youtube.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.clone.youtube.MainActivity
import com.clone.youtube.R
import com.clone.youtube.adapters.MainVideoListAdapter
import com.clone.youtube.databinding.FragmentHomeBinding
import com.clone.youtube.model.Channel
import com.clone.youtube.model.Comment
import com.clone.youtube.model.MainVideoListItem
import com.clone.youtube.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime

@AndroidEntryPoint
class HomeFragment : Fragment() {

    // This property is only valid between onCreateView and
    // onDestroyView.
    lateinit var mainActivity: MainActivity
    private lateinit var binding: FragmentHomeBinding
    val homeViewModel : HomeViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        mainActivity = activity as MainActivity
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.lifecycleOwner = this

        mainActivity.setSupportActionBar(binding.mainToolbar)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var mainVideoDataList : ArrayList<MainVideoListItem> = arrayListOf()

        mainVideoDataList.add(MainVideoListItem(thumbnailUrl = R.drawable.test1, title = "test1", view = 10000, createTime = LocalDateTime.of(2022, 1, 26, 19, 30, 20), channel = Channel(name = "테스터1", profileUrl = R.drawable.test1, subscribe = 10000)))
        mainVideoDataList.add(MainVideoListItem(thumbnailUrl = R.drawable.test2, title = "test2", view = 10000, createTime = LocalDateTime.of(2022, 1, 26, 19, 30, 20), channel = Channel(name = "테스터2", profileUrl = R.drawable.test2, subscribe = 10000)))
        mainVideoDataList.add(MainVideoListItem(thumbnailUrl = R.drawable.test3, title = "test3", view = 10000, createTime = LocalDateTime.of(2022, 1, 26, 19, 30, 20), channel = Channel(name = "테스터3", profileUrl = R.drawable.test3, subscribe = 10000)))
        mainVideoDataList.add(MainVideoListItem(thumbnailUrl = R.drawable.test4, title = "test4", view = 10000, createTime = LocalDateTime.of(2022, 1, 26, 19, 30, 20), channel = Channel(name = "테스터4", profileUrl = R.drawable.test4, subscribe = 10000)))
        mainVideoDataList.add(MainVideoListItem(thumbnailUrl = R.drawable.test5, title = "test5", view = 10000, createTime = LocalDateTime.of(2022, 1, 26, 19, 30, 20), channel = Channel(name = "테스터5", profileUrl = R.drawable.test5, subscribe = 10000)))
        mainVideoDataList.add(MainVideoListItem(thumbnailUrl = R.drawable.test6, title = "test6", view = 10000, createTime = LocalDateTime.of(2022, 1, 26, 19, 30, 20), channel = Channel(name = "테스터6", profileUrl = R.drawable.test6, subscribe = 10000)))
        mainVideoDataList.add(MainVideoListItem(thumbnailUrl = R.drawable.test7, title = "test7", view = 10000, createTime = LocalDateTime.of(2022, 1, 26, 19, 30, 20), channel = Channel(name = "테스터7", profileUrl = R.drawable.test7, subscribe = 10000)))
        mainVideoDataList.add(MainVideoListItem(thumbnailUrl = R.drawable.test8, title = "test8", view = 10000, createTime = LocalDateTime.of(2022, 1, 26, 19, 30, 20), channel = Channel(name = "테스터8", profileUrl = R.drawable.test8, subscribe = 10000)))
        mainVideoDataList.add(MainVideoListItem(thumbnailUrl = R.drawable.test9, title = "test9", view = 10000, createTime = LocalDateTime.of(2022, 1, 26, 19, 30, 20), channel = Channel(name = "테스터9", profileUrl = R.drawable.test9, subscribe = 10000)))
        mainVideoDataList.add(MainVideoListItem(thumbnailUrl = R.drawable.test10, title = "test10", view = 10000, createTime = LocalDateTime.of(2022, 1, 26, 19, 30, 20), channel = Channel(name = "테스터10", profileUrl = R.drawable.test10, subscribe = 10000)))

        homeViewModel.getVideos()



        binding.recyclerMainVideo.layoutManager = LinearLayoutManager(mainActivity)
        binding.recyclerMainVideo.adapter = MainVideoListAdapter()


        // toolbar profile 설정
        Glide.with(this).load(R.drawable.sample_profile).into(binding.fragmentToolbarProfile)


    }

}