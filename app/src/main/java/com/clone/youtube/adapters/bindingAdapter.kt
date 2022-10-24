package com.clone.youtube.adapters

import android.graphics.Bitmap
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.clone.youtube.extensions.toLiteralString
import com.clone.youtube.model.VideoListItem
import de.hdodenhof.circleimageview.CircleImageView
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import java.util.*
import kotlin.collections.ArrayList

object BindingAdapter {

    @BindingAdapter("setUnderVideoList")
    @JvmStatic
    fun setUnderVideoItemList(recyclerView: RecyclerView, itemList: ArrayList<VideoListItem>?) {
        val adapter = recyclerView.adapter as UnderVideoListAdapter
        // 시작할 때는 itemList가 null이기 때문에
        if (itemList == null) {
            adapter.dataSet = arrayListOf()
        } else {
            adapter.dataSet = itemList
        }
        adapter.notifyDataSetChanged()
    }

    @BindingAdapter("setChannelName", "setView", "setCreateTime")
    @JvmStatic
    fun setSubInfo(
        textView: TextView,
        channelName: String?,
        view: Int?,
        createTime: LocalDateTime
    ) {
        textView.text =
            channelName + " • 조회수 " + view?.toLiteralString() + "회 • " + createTime?.toLiteralString() + " 전"
    }

    @BindingAdapter("setPlayerView", "setPlayerCreateTime")
    @JvmStatic
    fun setPlayerSubInfo(textView: TextView, view: Int?, createTime: LocalDateTime) {
        textView.text = view?.toLiteralString() + "회 • " + createTime?.toLiteralString() + " 전"
    }

    @BindingAdapter("setOfflinePlayerView", "setOfflinePlayerCreateTime")
    @JvmStatic
    fun setOfflinePlayerSubInfo(textView: TextView, view: Int?, timestamp: Long) {
        val createTime = LocalDateTime.ofInstant(
            Instant.ofEpochMilli(timestamp),
            ZoneId.systemDefault()
        )
        textView.text = view?.toLiteralString() + "회 • " + createTime?.toLiteralString() + " 전"
    }

    @BindingAdapter("setLiteralString")
    @JvmStatic
    fun setLiteralString(textView: TextView, number: Int?) {
        textView.text = number?.toLiteralString()
    }

    @BindingAdapter("setChannelSubscribe")
    @JvmStatic
    fun setChannelSubscribe(textView: TextView, number: Int?) {
        textView.text = "구독자 " + number?.toLiteralString() + "명"
    }

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun imageUrl(imageView: ImageView, url: String?) {
        if (url != null) {
            Glide.with(imageView.context).load(url).into(imageView)
        }
    }

    @BindingAdapter("thumbnailBitmap")
    @JvmStatic
    fun thumbnailBitmap(imageView: ImageView, bitmap: Bitmap?) {
        if (bitmap != null) {
            Glide.with(imageView.context).load(bitmap).into(imageView)
        }
    }

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun imageUrl(circleImageView: CircleImageView, url: String?) {
        if (url != null) {
            Glide.with(circleImageView.context).load(url).into(circleImageView)
        }
    }
}