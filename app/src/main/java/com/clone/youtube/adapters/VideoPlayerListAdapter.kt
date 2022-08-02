package com.clone.youtube.adapters;

import android.content.res.Resources
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.clone.youtube.R
import com.clone.youtube.databinding.BottomSheetDialogEtcBinding
import com.clone.youtube.databinding.ListItemMainvideoBinding
import com.clone.youtube.databinding.ListItemPlayerBinding
import com.clone.youtube.extensions.toLiteralString
import com.clone.youtube.model.MainVideoListItem
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.time.Duration
import java.time.LocalDateTime


class VideoPlayerListAdapter(val dataSet: ArrayList<MainVideoListItem>) :
        MainVideoListAdapter(dataSet) {


        class PlayerViewHolder(private val binding: ListItemPlayerBinding, private val viewGroup: ViewGroup) :
                RecyclerView.ViewHolder(binding.root) {
                fun bind(data: MainVideoListItem) {
                        binding.listItemTitle.text = data.title
                        binding.listItemSubtitle.text = data.channel.name + " • " + integerToString(data.view) + "회" + " • " + data.time.toLiteralString() + " 전"

                        Glide.with(itemView).load(data.thumbnailUrl).into(binding.listItemVideoThumbnail)
                        Glide.with(itemView).load(data.channel.profileUrl).into(binding.listItemChannelImage)
                        binding.listItemButton.setOnClickListener {
                                bottomSheetDialog.show()
                        }
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
                        1 -> {

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
        override fun getItemCount() = dataSet.size

        override fun getItemViewType(position: Int): Int {
                return when (position) {
                    1 -> 0
                    else -> 1
                }
        }
        }