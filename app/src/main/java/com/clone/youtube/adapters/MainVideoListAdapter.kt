package com.clone.youtube.adapters;

import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.clone.youtube.MainActivity
import com.clone.youtube.R
import com.clone.youtube.databinding.BottomSheetDialogEtcBinding
import com.clone.youtube.databinding.ListItemMainvideoBinding
import com.clone.youtube.extensions.toLiteralString
import com.clone.youtube.model.MainVideoListItem
import com.clone.youtube.ui.home.HomeFragmentDirections
import com.clone.youtube.ui.play.PlayFragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.qualifiers.ActivityContext
import java.time.Duration
import java.time.LocalDateTime
import javax.inject.Inject


open class MainVideoListAdapter @Inject constructor() :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        var dataSet = arrayListOf<MainVideoListItem>()

        class ViewHolder(private val binding: ListItemMainvideoBinding, viewGroup: ViewGroup) :
                RecyclerView.ViewHolder(binding.root) {
                val bottomSheetView : BottomSheetDialogEtcBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.context), R.layout.bottom_sheet_dialog_etc, viewGroup, false)
                val bottomSheetDialog : BottomSheetDialog = BottomSheetDialog(viewGroup.context)
                init {
                        bottomSheetDialog.setContentView(bottomSheetView.root)
                }
                fun bind(data: MainVideoListItem) {
                        binding.listItem = data
                        binding.listItemButton.setOnClickListener {
                                bottomSheetDialog.show()
                        }
                        binding.root.setOnClickListener {
                                navigateToPlay(data)
                        }
                }

                private fun navigateToPlay(data : MainVideoListItem){ // 안되면 view parameter로 받아서
                        val direction = HomeFragmentDirections.actionFragmentHomeToFragmentPlay(data)
                        itemView.findNavController().navigate(direction)
                }
        }
        // Create new views (invoked by the layout manager)
        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
                // Create a new view, which defines the UI of the list item
                val binding : ListItemMainvideoBinding = DataBindingUtil.inflate(
                        LayoutInflater.from(viewGroup.context), R.layout.list_item_mainvideo,
                        viewGroup,
                        false
                )

                return ViewHolder(binding, viewGroup)
        }

        // Replace the contents of a view (invoked by the layout manager)
        override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {

                // Get element from your dataset at this position and replace the
                // contents of the view with that element
                (viewHolder as ViewHolder).bind(dataSet[position])
        }

        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount() = dataSet.size
}