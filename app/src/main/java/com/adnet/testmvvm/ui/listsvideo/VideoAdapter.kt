package com.adnet.testmvvm.ui.listsvideo

import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.adnet.testmvvm.R
import com.adnet.testmvvm.data.model.Video
import com.adnet.testmvvm.data.model.VideoYoutube
import com.adnet.testmvvm.databinding.ItemVideoBinding
import com.adnet.testmvvm.ui.adapter.VideoYoutubeDiffUtilCallback
import com.adnet.testmvvm.ui.base.BaseListViewHolder
import com.adnet.testmvvm.ui.base.ListAdapterInterface

class VideoAdapter(private val clickListener: (VideoYoutube) -> Unit) :
    ListAdapterInterface, ListAdapter<VideoYoutube, VideoAdapter.ListViewHolder>(VideoYoutubeDiffUtilCallback()
) {

    override fun getItemLayoutResource(viewType: Int) = R.layout.item_video

    override fun getViewHolderDataBinding(parent: ViewGroup, viewType: Int): ViewDataBinding =
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            getItemLayoutResource(viewType),
            parent, false
        )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {

        return ListViewHolder(
            binding = getViewHolderDataBinding(parent, viewType) as ItemVideoBinding
        )
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class ListViewHolder(
        binding: ItemVideoBinding
    ) : BaseListViewHolder<Video, ItemVideoBinding>(binding) {

        fun bind(video: VideoYoutube, clickListener: (VideoYoutube) -> Unit) {

            var mLastClickTime: Long = 0

            binding.viewModel = video

            itemView.setOnClickListener(object : View.OnClickListener {
                override fun onClick(view: View) {
                    if (SystemClock.elapsedRealtime() - mLastClickTime < 1000)
                        return

                    clickListener(video)
                    mLastClickTime = SystemClock.elapsedRealtime()

                }
            })
        }
    }
}
