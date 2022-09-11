package com.clone.youtube.adapters

import android.graphics.Bitmap
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.clone.youtube.extensions.toLiteralString
import com.clone.youtube.model.MainVideoListItem
import com.clone.youtube.model.PlayerVideoInfo
import com.clone.youtube.model.offline.OfflineVideo
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.ui.StyledPlayerView
import de.hdodenhof.circleimageview.CircleImageView
import java.time.LocalDateTime

object BindingAdapter {
    @BindingAdapter("setVideoList")
    @JvmStatic
    fun setVideoItemList(recyclerView: RecyclerView, itemList: ArrayList<MainVideoListItem>?) {
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

    @BindingAdapter("setOfflineVideoList")
    @JvmStatic
    fun setOfflineVideoList(recyclerView: RecyclerView, itemList: ArrayList<OfflineVideo>?) {
        val adapter = recyclerView.adapter as OfflineVideoListAdapter

        // 시작할 때는 itemList가 null이기 때문에
        if (itemList == null){
            adapter.dataSet = arrayListOf()
        }
        else {
            adapter.dataSet = itemList
        }
        adapter.notifyDataSetChanged()
    }


    @BindingAdapter("setChannelName", "setView", "setCreateTime")
    @JvmStatic
    fun setSubInfo(textView: TextView, channelName:String?, view:Int?, createTime : LocalDateTime){
        textView.text = channelName + " • 조회수 " + view?.toLiteralString() +"회 • " + createTime?.toLiteralString() + " 전"
    }

    @BindingAdapter("setPlayerView", "setPlayerCreateTime")
    @JvmStatic
    fun setPlayerSubInfo(textView: TextView, view:Int?, createTime : LocalDateTime){
        textView.text = view?.toLiteralString() +"회 • " + createTime?.toLiteralString() + " 전"
    }

    @BindingAdapter("setLiteralString")
    @JvmStatic
    fun setLiteralString(textView: TextView, number: Int?){
        textView.text = number?.toLiteralString()
    }

    @BindingAdapter("setChannelSubscribe")
    @JvmStatic
    fun setChannelSubscribe(textView: TextView, number: Int?){
        textView.text = "구독자 " + number?.toLiteralString() + "명"
    }


    @BindingAdapter("imageUrl")
    @JvmStatic
    fun imageUrl(imageView: ImageView, url: String?){
        if (url != null) {
            Glide.with(imageView.context).load(url).into(imageView)
        }
    }

    @BindingAdapter("thumbnailBitmap")
    @JvmStatic
    fun thumbnailBitmap(imageView: ImageView, bitmap: Bitmap?){
        if (bitmap != null) {
            Log.d("XX", "bitmap")
            Glide.with(imageView.context).load(bitmap).into(imageView)
        }
    }

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun imageUrl(circleImageView: CircleImageView, url: String?){
        if (url != null) {
            Glide.with(circleImageView.context).load(url).into(circleImageView)
        }
    }

    @BindingAdapter("setPlayerInfo", "setPlayerInfoFromList", "setPlayerVideoList")
    @JvmStatic
    fun setPlayerItemList(recyclerView: RecyclerView, playerVideoInfo: PlayerVideoInfo?, playerVideoInfoFromList : MainVideoListItem?, itemList: ArrayList<MainVideoListItem>?) {
        val adapter = recyclerView.adapter as VideoPlayerListAdapter
        adapter.playerVideoInfo = playerVideoInfo
        adapter.playerVideoInfoEtc = playerVideoInfoFromList
        // 시작할 때는 itemList가 null이기 때문에
        if (itemList == null){
            adapter.dataSet = arrayListOf()
        }
        else {
            adapter.dataSet = itemList
        }
        adapter.notifyDataSetChanged()
    }

    @BindingAdapter("setVideoUrl", "setPlayWhenReady", "setCurrentWindow", "setPlayBackPosition")
    @JvmStatic
    fun setExoPlayerVideoUrl(playerView: PlayerView, url:String?, playWhenReady : Boolean, currentWindow : Int, playBackPosition : Long){
        val exoPlayer = playerView.player
        if (url == null){

        }
        else {
            val mediaItem = MediaItem.fromUri(url)
            exoPlayer?.setMediaItem(mediaItem)
            exoPlayer?.playWhenReady = playWhenReady
            exoPlayer?.seekTo(currentWindow, playBackPosition)
            exoPlayer?.prepare()
        }
    }
}