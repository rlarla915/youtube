package com.clone.youtube.adapters;

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.clone.youtube.R
import com.clone.youtube.databinding.BottomSheetDialogEtcBinding
import com.clone.youtube.databinding.ListItemVideoBinding
import com.clone.youtube.model.VideoListItem
import com.clone.youtube.ui.home.HomeFragmentDirections
import com.google.android.material.bottomsheet.BottomSheetDialog

open class UnderVideoListAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var dataSet = arrayListOf<VideoListItem>()

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

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ListItemVideoBinding = DataBindingUtil.inflate(
            LayoutInflater.from(viewGroup.context), R.layout.list_item_video,
            viewGroup,
            false
        )

        return ViewHolder(binding, viewGroup)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        (viewHolder as ViewHolder).bind(dataSet[position])
    }

    override fun getItemCount() = dataSet.size
}