package com.clone.youtube.ui.storage.offline

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.clone.youtube.MainActivity
import com.clone.youtube.R
import com.clone.youtube.adapters.OfflineVideoListAdapter
import com.clone.youtube.databinding.FragmentOfflineStorageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OfflineStorageFragment : Fragment() {

    // This property is only valid between onCreateView and
    // onDestroyView.
    lateinit var mainActivity: MainActivity
    private lateinit var binding: FragmentOfflineStorageBinding
    val offlineStorageViewModel : OfflineStorageViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        mainActivity = activity as MainActivity
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_offline_storage, container, false)
        binding.viewModel = offlineStorageViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        Log.d("QQ", "ZZZZZ")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        offlineStorageViewModel.getOfflineVideo()
        binding.recyclerOfflineVideo.layoutManager = LinearLayoutManager(mainActivity)
        binding.recyclerOfflineVideo.adapter = OfflineVideoListAdapter()
    }
}