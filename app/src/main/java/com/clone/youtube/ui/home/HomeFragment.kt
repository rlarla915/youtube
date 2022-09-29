package com.clone.youtube.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.clone.youtube.MainActivity
import com.clone.youtube.R
import com.clone.youtube.adapters.MainVideoListAdapter
import com.clone.youtube.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    // This property is only valid between onCreateView and
    // onDestroyView.
    lateinit var mainActivity: MainActivity
    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel : HomeViewModel by viewModels()
    private val adapter = MainVideoListAdapter()


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        mainActivity = activity as MainActivity
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.viewModel = homeViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        mainActivity.setSupportActionBar(binding.mainToolbar)
        binding.recyclerMainVideo.layoutManager = LinearLayoutManager(mainActivity)
        binding.recyclerMainVideo.adapter = adapter
        initObserve()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // toolbar profile 설정
        Glide.with(this).load(R.drawable.sample_profile).into(binding.fragmentToolbarProfile)
    }

    private fun initObserve(){
        homeViewModel.mainVideoList.observe(viewLifecycleOwner){
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

}