package com.clone.youtube.adapters;

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.clone.youtube.R
import com.clone.youtube.databinding.BottomSheetDialogEtcBinding
import com.clone.youtube.databinding.ListItemOfflineStorageBinding
import com.clone.youtube.model.offline.OfflineVideo
import com.google.android.material.bottomsheet.BottomSheetDialog

class OfflineVideoListAdapter :
    PagingDataAdapter<OfflineVideo, OfflineVideoListAdapter.ViewHolder>(DiffCallback()) {

    class ViewHolder(
        private val binding: ListItemOfflineStorageBinding,
        private val viewGroup: ViewGroup
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: OfflineVideo) {
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

        private fun navigateToPlay(data: OfflineVideo) {
            // [fix] : need to add
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemOfflineStorageBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup,
            false
        )

        return ViewHolder(binding, viewGroup)
    }

    private class DiffCallback : DiffUtil.ItemCallback<OfflineVideo>() {
        override fun areItemsTheSame(oldItem: OfflineVideo, newItem: OfflineVideo): Boolean {
            return oldItem.key == newItem.key
        }

        override fun areContentsTheSame(oldItem: OfflineVideo, newItem: OfflineVideo): Boolean {
            return oldItem == newItem
        }
    }
}