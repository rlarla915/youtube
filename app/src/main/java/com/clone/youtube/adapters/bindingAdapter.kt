package com.clone.youtube.adapters

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.clone.youtube.model.MainVideoListItem

object BindingAdapter {
    @BindingAdapter("setList")
    @JvmStatic
    fun setRecyclerViewItemList(recyclerView: RecyclerView, itemList: ArrayList<MainVideoListItem>?) {
        if (recyclerView.adapter == null) {
            recyclerView.adapter = MainVideoListAdapter()
        }
        val adapter = recyclerView.adapter as MainVideoListAdapter
        
        // 시작할 때는 itemList가 null이기 때문에
        if (itemList == null){
            adapter.dataSet = arrayListOf()
        }
        else {
            adapter.dataSet = itemList
        }
        adapter.notifyDataSetChanged()
    }
}