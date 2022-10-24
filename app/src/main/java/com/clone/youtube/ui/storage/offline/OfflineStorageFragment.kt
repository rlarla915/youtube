package com.clone.youtube.ui.storage.offline

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.clone.youtube.ui.main.MainActivity
import com.clone.youtube.R
import com.clone.youtube.adapters.OfflineVideoListAdapter
import com.clone.youtube.databinding.FragmentOfflineStorageBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OfflineStorageFragment : Fragment() {

    private val mainActivity: MainActivity by lazy { activity as MainActivity }
    private lateinit var _binding: FragmentOfflineStorageBinding
    private val binding get() = _binding
    private val offlineStorageViewModel: OfflineStorageViewModel by viewModels()
    private val adapter = OfflineVideoListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_offline_storage, container, false)
        binding.viewModel = offlineStorageViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.recyclerOfflineVideo.layoutManager = LinearLayoutManager(mainActivity)
        binding.recyclerOfflineVideo.adapter = adapter

        initAdapter()
        return binding.root
    }

    private fun initAdapter() {
        lifecycleScope.launch {
            offlineStorageViewModel.getOfflineVideo().collectLatest {
                adapter.submitData(it)
            }
        }
    }
}