package com.clone.youtube.adapters;

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.clone.youtube.R
import com.clone.youtube.databinding.ListItemCommentBinding
import com.clone.youtube.extensions.toLiteralString
import com.clone.youtube.model.Comment

class CommentListAdapter(private val dataSet: ArrayList<Comment>) :
    RecyclerView.Adapter<CommentListAdapter.ViewHolder>() {

    class ViewHolder(
        private val binding: ListItemCommentBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Comment) {
            binding.listItemNumLikes.text = " ${data.likes.toLiteralString()}"
            binding.listItemContext.text = data.text
            binding.listItemName.text = Resources.getSystem().getString(
                R.string.comment_list_item_name,
                data.channel.name,
                data.time.toLiteralString()
            )
            Glide.with(itemView).load(data.channel.profileUrl).into(binding.listItemChannelImage)
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ListItemCommentBinding = DataBindingUtil.inflate(
            LayoutInflater.from(viewGroup.context), R.layout.list_item_comment,
            viewGroup,
            false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(dataSet[position])
    }

    override fun getItemCount() = dataSet.size

}