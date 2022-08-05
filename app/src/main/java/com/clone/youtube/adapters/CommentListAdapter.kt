package com.clone.youtube.adapters;

import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.clone.youtube.MainActivity
import com.clone.youtube.R
import com.clone.youtube.databinding.BottomSheetDialogEtcBinding
import com.clone.youtube.databinding.ListItemCommentBinding
import com.clone.youtube.databinding.ListItemMainvideoBinding
import com.clone.youtube.extensions.toLiteralString
import com.clone.youtube.model.Comment
import com.clone.youtube.model.MainVideoListItem
import com.clone.youtube.ui.play.PlayFragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.time.Duration
import java.time.LocalDateTime


class CommentListAdapter(val dataSet: ArrayList<Comment>) :
        RecyclerView.Adapter<CommentListAdapter.ViewHolder>() {

        class ViewHolder(private val binding: ListItemCommentBinding, private val viewGroup: ViewGroup) :
                RecyclerView.ViewHolder(binding.root) {
                fun bind(data: Comment) {
                        binding.listItemNumLikes.text = " " + integerToString(data.likes)
                        binding.listItemContext.text = data.text
                        binding.listItemName.text = data.channel.name + " • " + data.time.toLiteralString() + " 전"
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
                        val binding : ListItemCommentBinding = DataBindingUtil.inflate(
                                LayoutInflater.from(viewGroup.context), R.layout.list_item_comment,
                                viewGroup,
                                false
                        )

                        return ViewHolder(binding, viewGroup)
                }

                // Replace the contents of a view (invoked by the layout manager)
                override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
                        viewHolder.bind(dataSet[position])
                }

                // Return the size of your dataset (invoked by the layout manager)
                override fun getItemCount() = dataSet.size

}