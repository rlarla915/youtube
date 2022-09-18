package com.clone.youtube.adapters;

import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.clone.youtube.MainActivity
import com.clone.youtube.R
import com.clone.youtube.databinding.BottomSheetDialogEtcBinding
import com.clone.youtube.databinding.ListItemMainvideoBinding
import com.clone.youtube.databinding.ListItemPlayerBinding
import com.clone.youtube.extensions.toLiteralString
import com.clone.youtube.model.Channel
import com.clone.youtube.model.Comment
import com.clone.youtube.model.MainVideoListItem
import com.clone.youtube.model.PlayerVideoInfo
import com.clone.youtube.ui.play.BottomSheetDialogComments
import com.clone.youtube.ui.play.OnPlayerClick
import com.clone.youtube.ui.play.PlayFragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.qualifiers.ActivityContext
import java.time.Duration
import java.time.LocalDateTime
import javax.inject.Inject


class VideoPlayerListAdapter(private val clickListener: OnPlayerClick) :
        MainVideoListAdapter() {
        var playerVideoInfo : PlayerVideoInfo? = null
        var playerVideoInfoEtc : MainVideoListItem? = null
        inner class PlayerViewHolder(private val binding: ListItemPlayerBinding, private val viewGroup: ViewGroup) :
                RecyclerView.ViewHolder(binding.root) {
                fun bind(playerVideoInfo : PlayerVideoInfo?, playerVideoInfoFromList : MainVideoListItem?) {
                        binding.videoInfo = playerVideoInfo
                        binding.videoInfoFromList = playerVideoInfoFromList
                        binding.videoChannelTextSubscribe.setOnClickListener {
                                // need to add
                        }

                        binding.videoCommentBox.setOnClickListener {
                                var bottomSheetDialogComments = BottomSheetDialogComments()
                                val mainActivity = viewGroup.context as MainActivity
                                bottomSheetDialogComments.show(mainActivity.supportFragmentManager, "comments")
                        }
                        binding.videoTextOfflineSave.setOnClickListener {
                                clickListener.offlineStorage()
                        }
                }
        }



        // Create new views (invoked by the layout manager)
        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
                return when (viewType) {
                        0 -> {
                                val binding : ListItemPlayerBinding = DataBindingUtil.inflate(
                                        LayoutInflater.from(viewGroup.context), R.layout.list_item_player,
                                        viewGroup,
                                        false
                                )

                                PlayerViewHolder(binding, viewGroup)
                        }
                        else -> {
                                super.onCreateViewHolder(viewGroup, viewType)
                        }
                }

        }

        // Replace the contents of a view (invoked by the layout manager)
        @RequiresApi(Build.VERSION_CODES.O)
        override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
                when (position) {
                        0 -> {
                                (viewHolder as PlayerViewHolder).bind(playerVideoInfo, playerVideoInfoEtc)
                        }
                        else -> {
                                super.onBindViewHolder(viewHolder, position-1)
                        }
                }

                // Get element from your dataset at this position and replace the
                // contents of the view with that element
                //viewHolder.bind(dataSet[position])
        }

        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount() = super.dataSet.size + 1

        override fun getItemViewType(position: Int): Int {
                return when (position) {
                    0 -> 0
                    else -> 1
                }
        }
        }