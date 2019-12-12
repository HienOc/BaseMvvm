package com.adnet.testmvvm.ui.home

import android.os.SystemClock
import android.view.View
import android.view.ViewGroup
import com.adnet.testmvvm.R
import com.adnet.testmvvm.data.model.Video
import com.adnet.testmvvm.databinding.ItemNoteBinding
import com.adnet.testmvvm.ui.base.BaseListAdapter
import com.adnet.testmvvm.ui.base.BaseListViewHolder

class MainAdapter(private val clickListener: (Video) -> Unit) :
    BaseListAdapter<Video, ItemNoteBinding, MainAdapter.ListViewHolder>() {

    override fun getItemLayoutResource(viewType: Int) = R.layout.item_note

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {

        return ListViewHolder(
            binding = getViewHolderDataBinding(parent, viewType) as ItemNoteBinding
        )
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class ListViewHolder(
        binding: ItemNoteBinding
    ) : BaseListViewHolder<Video, ItemNoteBinding>(binding) {

        fun bind(video: Video, clickListener: (Video) -> Unit) {

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
