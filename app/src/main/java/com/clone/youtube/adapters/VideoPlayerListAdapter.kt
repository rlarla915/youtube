package com.clone.youtube.adapters;

import android.content.res.Resources
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
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
import com.clone.youtube.ui.play.BottomSheetDialogComments
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.time.Duration
import java.time.LocalDateTime


class VideoPlayerListAdapter(val videoInfo : MainVideoListItem, val VideodataSet: ArrayList<MainVideoListItem>) :
        MainVideoListAdapter(VideodataSet) {

        class PlayerViewHolder(private val binding: ListItemPlayerBinding, private val viewGroup: ViewGroup) :
                RecyclerView.ViewHolder(binding.root) {
                fun bind(data: MainVideoListItem) {
                        binding.videoTextTitle.text = data.title
                        binding.videoTextSubtitle.text = "조회수 " + integerToString(data.view)+"회 • " + data.createTime.toLiteralString() + " 전"
                        binding.videoTextLike.text = "1.5만"//integerToString(data.likes)
                        Glide.with(itemView).load(data.channel.profileUrl).into(binding.videoChannelImage)
                        binding.videoChannelName.text = data.channel.name
                        binding.videoChannelNumSubscribe.text = "구독자 " + integerToString(data.channel.subscribe)+"명"
                        binding.videoChannelTextSubscribe.setOnClickListener {
                                // need to add
                        }
                        binding.videoCommentNumComment.text = "12"//data.comments.size.toString()

                        binding.videoCommentBox.setOnClickListener {
                                var bottomSheetDialogComments = BottomSheetDialogComments()
                                var mainActivity = viewGroup.context as MainActivity
                                bottomSheetDialogComments.show(mainActivity.supportFragmentManager, "comments")
                        }
                        var bestComment : Comment = Comment(Channel("침착맨", R.drawable.sample_profile, 150000), LocalDateTime.of(2022, 1, 26, 19, 30, 20), 150000, "너무 재밌어용~")
                        Glide.with(itemView).load(bestComment.channel.profileUrl).into(binding.videoCommentImage)
                        binding.videoCommentText.text = bestComment.text
                }
                fun integerToString(number : Int) : String{
                        return when{
                                number >= 1E8 -> "${String.format("%.1f", (number.toFloat()/1E8))}억"
                                number >= 1E4 -> "${String.format("%.1f", (number.toFloat()/1E4))}만"
                                number >= 1E3 -> "${String.format("%.1f", (number.toFloat()/1E8))}천"
                                else -> number.toString()
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
        override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
                when (position) {
                        0 -> {
                                (viewHolder as PlayerViewHolder).bind(videoInfo)
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
        override fun getItemCount() = VideodataSet.size + 1

        override fun getItemViewType(position: Int): Int {
                return when (position) {
                    0 -> 0
                    else -> 1
                }
        }
        }