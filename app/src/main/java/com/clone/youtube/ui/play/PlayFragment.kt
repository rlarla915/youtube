package com.clone.youtube.ui.play

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.clone.youtube.MainActivity
import com.clone.youtube.R
import com.clone.youtube.adapters.MainVideoListAdapter
import com.clone.youtube.databinding.FragmentHomeBinding
import com.clone.youtube.databinding.FragmentPlayBinding
import com.clone.youtube.model.Channel
import com.clone.youtube.model.MainVideoListItem
import com.clone.youtube.ui.home.HomeViewModel
import java.time.LocalDateTime

class PlayFragment : Fragment() {

    // This property is only valid between onCreateView and
    // onDestroyView.
    lateinit var mainActivity: MainActivity
    private lateinit var binding: FragmentPlayBinding
    val homeViewModel : HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mainActivity = activity as MainActivity
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_play, container, false)
        binding.lifecycleOwner = this


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}