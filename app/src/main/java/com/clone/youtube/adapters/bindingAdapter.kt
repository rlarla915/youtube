package com.clone.youtube.adapters

import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.clone.youtube.model.MainVideoListItem


@BindingAdapter("setList")
fun setRecyclerViewItemList(recyclerView: RecyclerView, itemList : MutableLiveData<List<MainVideoListItem>>){
    recyclerView.adapter?.run {
        if (this is MainVideoListAdapter){
            this.dataSet = itemList.value!!
            this.notifyDataSetChanged() // list adpater를 대신 사용할 수 있음.
        }
    }
}