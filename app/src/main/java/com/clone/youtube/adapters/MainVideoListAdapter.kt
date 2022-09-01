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
import com.clone.youtube.databinding.ListItemMainvideoBinding
import com.clone.youtube.extensions.toLiteralString
import com.clone.youtube.model.MainVideoListItem
import com.clone.youtube.ui.play.PlayFragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.time.Duration
import java.time.LocalDateTime


open class MainVideoListAdapter() :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        var dataSet = arrayListOf<MainVideoListItem>()

        class ViewHolder(private val binding: ListItemMainvideoBinding, private val viewGroup: ViewGroup) :
                RecyclerView.ViewHolder(binding.root) {
                val bottomSheetView : BottomSheetDialogEtcBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.context), R.layout.bottom_sheet_dialog_etc, viewGroup, false)
                val bottomSheetDialog : BottomSheetDialog = BottomSheetDialog(viewGroup.context)
                init {
                        bottomSheetDialog.setContentView(bottomSheetView.root)
                }
                fun bind(data: MainVideoListItem) {
                        binding.listItemTitle.text = data.title
                        binding.listItemSubtitle.text = data.channel.name + " • " + integerToString(data.view) + "회" + " • " + data.createTime.toLiteralString() + " 전"

                        Glide.with(itemView).load(data.thumbnailUrl).into(binding.listItemVideoThumbnail)
                        Glide.with(itemView).load(data.channel.profileUrl).into(binding.listItemChannelImage)
                        binding.listItemButton.setOnClickListener {
                                bottomSheetDialog.show()
                        }
                        binding.root.setOnClickListener {
                                val playFragment : PlayFragment = PlayFragment()
                                val mainActivity = viewGroup.context as MainActivity
                                mainActivity.supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment_activity_main, playFragment.apply { arguments = Bundle().apply { putParcelable("videoInfo", data)} }).commit()
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