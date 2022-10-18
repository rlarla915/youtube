package com.clone.youtube.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.clone.youtube.MainActivity
import com.clone.youtube.R
import com.clone.youtube.adapters.VideoListAdapter
import com.clone.youtube.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val mainActivity: MainActivity by lazy { activity as MainActivity }
    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()
    private val adapter = VideoListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.viewModel = homeViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        mainActivity.setSupportActionBar(binding.mainToolbar)

        binding.recyclerMainVideo.layoutManager = LinearLayoutManager(mainActivity)
        binding.recyclerMainVideo.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserve()
        // [fix] set toolbar profile. load with real profile url
        Glide.with(this).load(R.drawable.sample_profile).into(binding.fragmentToolbarProfile)
    }

    private fun initObserve() {
        homeViewModel.mainVideoList.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

}