package com.adnet.testmvvm.ui.adapter

import com.adnet.testmvvm.data.model.Video


class VideoDiffUtilCallback : BaseDiffUtil<Video>() {
    override fun areItemsTheSame(oldItem: Video, newItem: Video): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Video, newItem: Video): Boolean {
        return oldItem == newItem
    }
}
