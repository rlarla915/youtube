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
        PagingDataAdapter<OfflineVideo, OfflineVideoListAdapter.OfflineVideoViewHolder>(OfflineVideoDiffCallback()) {

        class OfflineVideoViewHolder(private val binding: ListItemOfflineStorageBinding, viewGroup: ViewGroup) :
                RecyclerView.ViewHolder(binding.root) {
                val bottomSheetView: BottomSheetDialogEtcBinding = DataBindingUtil.inflate(
                        LayoutInflater.from(viewGroup.context),
                        R.layout.bottom_sheet_dialog_etc,
                        viewGroup,
                        false
                )
                val bottomSheetDialog: BottomSheetDialog = BottomSheetDialog(viewGroup.context)

                init {
                        bottomSheetDialog.setContentView(bottomSheetView.root)
                }

                fun bind(data: OfflineVideo) {
                        binding.listItem = data
                        binding.listItemButton.setOnClickListener {
                                bottomSheetDialog.show()
                        }
                        binding.root.setOnClickListener {
                                navigateToPlay(data)
                        }
                }

                private fun navigateToPlay(data: OfflineVideo) { // 안되면 view parameter로 받아서
                        //val direction = HomeFragmentDirections.actionFragmentHomeToFragmentPlay(data)
                        //itemView.findNavController().navigate(direction)
                }
        }

        override fun onBindViewHolder(holder: OfflineVideoViewHolder, position: Int) {
                val item = getItem(position)
                if (item != null){
                        holder.bind(item)
                }
        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): OfflineVideoViewHolder {
                val binding = ListItemOfflineStorageBinding.inflate(
                        LayoutInflater.from(viewGroup.context),
                        viewGroup,
                        false
                )

                return OfflineVideoViewHolder(binding, viewGroup)
        }
}

private class OfflineVideoDiffCallback : DiffUtil.ItemCallback<OfflineVideo>() {
        override fun areItemsTheSame(oldItem: OfflineVideo, newItem: OfflineVideo): Boolean {
                return oldItem.key == newItem.key
        }

        override fun areContentsTheSame(oldItem: OfflineVideo, newItem: OfflineVideo): Boolean {
                return oldItem == newItem
        }
}