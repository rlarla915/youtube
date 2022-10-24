package com.clone.youtube.adapters;

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.clone.youtube.R
import com.clone.youtube.databinding.BottomSheetDialogEtcBinding
import com.clone.youtube.databinding.ListItemVideoBinding
import com.clone.youtube.model.VideoListItem
import com.clone.youtube.ui.home.HomeFragmentDirections
import com.google.android.material.bottomsheet.BottomSheetDialog

class VideoListAdapter :
    PagingDataAdapter<VideoListItem, VideoListAdapter.ViewHolder>(
        DiffCallback()
    ) {

    class ViewHolder(
        private val binding: ListItemVideoBinding,
        private val viewGroup: ViewGroup
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: VideoListItem) {
            binding.listItem = data
            binding.listItemButton.setOnClickListener {
                val bottomSheetView: BottomSheetDialogEtcBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(viewGroup.context),
                    R.layout.bottom_sheet_dialog_etc,
                    viewGroup,
                    false
                )
                val bottomSheetDialog: BottomSheetDialog = BottomSheetDialog(viewGroup.context)
                bottomSheetDialog.setContentView(bottomSheetView.root)
                bottomSheetDialog.show()
            }
            binding.root.setOnClickListener {
                navigateToPlay(data)
            }
        }

        private fun navigateToPlay(data: VideoListItem) {
            val direction = HomeFragmentDirections.actionFragmentHomeToFragmentPlay(data)
            itemView.findNavController().navigate(direction)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ListItemVideoBinding = ListItemVideoBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup,
            false
        )

        return ViewHolder(binding, viewGroup)
    }

    private class DiffCallback : DiffUtil.ItemCallback<VideoListItem>() {
        override fun areItemsTheSame(oldItem: VideoListItem, newItem: VideoListItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: VideoListItem,
            newItem: VideoListItem
        ): Boolean {
            return oldItem == newItem
        }
    }
}

