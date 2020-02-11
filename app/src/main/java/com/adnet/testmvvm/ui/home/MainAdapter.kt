package com.adnet.testmvvm.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.adnet.testmvvm.R
import com.adnet.testmvvm.data.model.Video
import com.adnet.testmvvm.databinding.ItemNoteBinding
import com.adnet.testmvvm.ui.adapter.VideoDiffUtilCallback
import com.adnet.testmvvm.ui.base.BaseListViewHolder
import com.adnet.testmvvm.ui.base.ListAdapterInterface
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.SharePreference
import kotlinx.android.synthetic.main.item_note.view.*
import org.greenrobot.eventbus.EventBus
import setSafeOnClickListener

class MainAdapter(private val clickListener: (Video) -> Unit) :
    ListAdapterInterface, ListAdapter<Video, MainAdapter.ListViewHolder>(VideoDiffUtilCallback()) {

    override fun getItemLayoutResource(viewType: Int) = R.layout.item_note

    override fun getViewHolderDataBinding(parent: ViewGroup, viewType: Int): ViewDataBinding =
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            getItemLayoutResource(viewType),
            parent, false
        )

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

            binding.viewModel = video

            if(SharePreference.CHECK_ITEM) {
                itemView.checkbox.visibility=View.VISIBLE
                itemView.checkbox.startAnimation(TranslateAnimation(
                    -50f,
                    0f,
                    0f,
                    0f
                ).apply {
                    duration = 400
                })
            }
            else itemView.checkbox.visibility=View.GONE

            itemView.checkbox.isChecked=false

            itemView.setSafeOnClickListener {
                if(SharePreference.CHECK_ITEM){
                    itemView.checkbox.isChecked = !itemView.checkbox.isChecked
                    if(itemView.checkbox.isChecked) {
                        SharePreference.CHECK_STATUS=true
                        EventBus.getDefault().post(video)
                    }
                    else{
                        SharePreference.CHECK_STATUS=false
                        EventBus.getDefault().post(video)
                    }
                }
            }

            itemView.setOnLongClickListener {
                SharePreference.CHECK_ITEM=true
                itemView.checkbox.isChecked=false
                clickListener(video)
                true
            }
        }
    }
}
