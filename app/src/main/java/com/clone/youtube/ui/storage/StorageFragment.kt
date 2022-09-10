package com.clone.youtube.ui.storage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
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
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime
import javax.inject.Inject

@AndroidEntryPoint
class StorageFragment : Fragment() {

    // This property is only valid between onCreateView and
    // onDestroyView.
    lateinit var mainActivity: MainActivity
    private lateinit var binding: FragmentHomeBinding
    val storageViewModel : StorageViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        mainActivity = activity as MainActivity
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.viewModel = storageViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        mainActivity.setSupportActionBar(binding.mainToolbar)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        storageViewModel.loadMainVideoList()

        binding.recyclerMainVideo.layoutManager = LinearLayoutManager(mainActivity)
        binding.recyclerMainVideo.adapter = MainVideoListAdapter()
        // toolbar profile 설정
        Glide.with(this).load(R.drawable.sample_profile).into(binding.fragmentToolbarProfile)
    }

}