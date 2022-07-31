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
import com.clone.youtube.databinding.ListItemMainvideoBinding
import com.clone.youtube.extensions.toLiteralString
import com.clone.youtube.model.MainVideoListItem
import java.time.Duration
import java.time.LocalDateTime


class MainVideoListAdapter(val dataSet: ArrayList<MainVideoListItem>) :
        RecyclerView.Adapter<MainVideoListAdapter.ViewHolder>() {

        class ViewHolder(private val binding: ListItemMainvideoBinding) :
                RecyclerView.ViewHolder(binding.root) {
                fun bind(data: MainVideoListItem) {
                        binding.listItemTitle.text = data.title
                        binding.listItemSubtitle.text = data.channel.name + " • " + integerToString(data.view) + "회" + " • " + data.time.toLiteralString() + " 전"

                        Glide.with(itemView).load(data.thumbnailUrl).into(binding.listItemVideoThumbnail)
                        Glide.with(itemView).load(data.channel.profileUrl).into(binding.listItemChannelImage)

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
                override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
                        // Create a new view, which defines the UI of the list item
                        val binding : ListItemMainvideoBinding = DataBindingUtil.inflate(
                                LayoutInflater.from(viewGroup.context), R.layout.list_item_mainvideo,
                                viewGroup,
                                false
                        )

                        return ViewHolder(binding)
                }

                // Replace the contents of a view (invoked by the layout manager)
                override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

                        // Get element from your dataset at this position and replace the
                        // contents of the view with that element
                        viewHolder.bind(dataSet[position])
                }

                // Return the size of your dataset (invoked by the layout manager)
                override fun getItemCount() = dataSet.size
        }