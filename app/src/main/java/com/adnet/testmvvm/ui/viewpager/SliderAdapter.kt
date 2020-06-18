package com.adnet.testmvvm.ui.viewpager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adnet.testmvvm.R
import com.adnet.testmvvm.data.model.entities.BannerModel
import com.smarteist.autoimageslider.SliderViewAdapter
import kotlinx.android.synthetic.main.item_page_home.view.*
import setImageUrl

class SliderAdapter(private var image: List<BannerModel>) :
    SliderViewAdapter<SliderAdapter.SliderAdapterVH>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup): SliderAdapterVH {
        val itemView = LayoutInflater.from(
            viewGroup.context
        ).inflate(R.layout.item_page_home, viewGroup, false)
        return SliderAdapterVH(
            itemView
        )
    }

    override fun getCount(): Int = image.size

    override fun onBindViewHolder(viewHolder: SliderAdapterVH, position: Int) {
        viewHolder.bindData(image, position)
    }

    class SliderAdapterVH(itemView: View) : SliderViewAdapter.ViewHolder(itemView) {
        fun bindData(players: List<BannerModel>, position: Int) {
            itemView.imageViewHomeTop.setImageUrl(players[position].bannerImage)
        }
    }
}
