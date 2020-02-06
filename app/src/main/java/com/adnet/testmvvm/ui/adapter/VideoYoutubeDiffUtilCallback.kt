package com.adnet.testmvvm.ui.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.adnet.testmvvm.data.model.VideoYoutube

class VideoYoutubeDiffUtilCallback : DiffUtil.ItemCallback<VideoYoutube>() {
    override fun areItemsTheSame(oldItem: VideoYoutube, newItem: VideoYoutube): Boolean {
        return oldItem.idVideo == newItem.idVideo
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: VideoYoutube, newItem: VideoYoutube): Boolean {
        return oldItem == newItem
    }
}
