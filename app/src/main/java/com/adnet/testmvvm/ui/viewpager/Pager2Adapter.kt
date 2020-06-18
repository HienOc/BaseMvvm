package com.adnet.testmvvm.ui.viewpager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adnet.testmvvm.R
import com.adnet.testmvvm.data.model.entities.BannerModel
import kotlinx.android.synthetic.main.item_page_home.view.*
import setImageUrl

@Suppress("DEPRECATION")
class Pager2Adapter(
    private var image: List<BannerModel>
) : RecyclerView.Adapter<Pager2Adapter.ViewHolder>() {

    private lateinit var holder: ViewHolder

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(
            viewGroup.context
        ).inflate(R.layout.item_page_home, viewGroup, false)
        return ViewHolder(
            itemView,
            viewGroup.context
        )
    }

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.bindData(image, holder.adapterPosition)
        this.holder=holder
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun getItemCount() = image.size

    class ViewHolder(itemView: View, private val context: Context) :
        RecyclerView.ViewHolder(itemView) {

        fun bindData(players: List<BannerModel>, position: Int) {
            itemView.imageViewHomeTop.setImageUrl(players[position].bannerImage)
        }
    }

}