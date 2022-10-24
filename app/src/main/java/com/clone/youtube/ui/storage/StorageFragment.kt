package com.clone.youtube.ui.storage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.clone.youtube.ui.main.MainActivity
import com.clone.youtube.R
import com.clone.youtube.databinding.FragmentStorageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StorageFragment : Fragment() {
    private val mainActivity: MainActivity by lazy { activity as MainActivity }
    private lateinit var _binding: FragmentStorageBinding
    private val binding get() = _binding
    private val storageViewModel: StorageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_storage, container, false)
        binding.viewModel = storageViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        mainActivity.setSupportActionBar(binding.mainToolbar)

        initObserve()

        return binding.root
    }

    private fun initObserve() {
        storageViewModel.checkNavigateToOfflineStorage.observe(viewLifecycleOwner, Observer {
            val direction = StorageFragmentDirections.actionFragmentStorageToOfflineStorage()
            findNavController().navigate(direction)
        })
    }

}