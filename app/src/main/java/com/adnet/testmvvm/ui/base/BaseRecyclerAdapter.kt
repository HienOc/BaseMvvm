package com.adnet.testmvvm.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<
        T,
        ViewBinding : ViewDataBinding,
        viewHolder : BaseViewHolder<T, ViewBinding>
        > : RecyclerView.Adapter<viewHolder>() {

    private val items = ArrayList<T>()

    abstract fun getItemLayoutResource(viewType: Int): Int

    override fun getItemCount(): Int = items.size

//    override fun onBindViewHolder(holder: viewHolder, position: Int) =
//        holder.onBindData(items[position])

    override fun onBindViewHolder(holder: viewHolder, position: Int) =
        holder.onBindData(position,items[position])


    protected fun getViewHolderDataBinding(parent: ViewGroup, viewType: Int): ViewDataBinding =
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            getItemLayoutResource(viewType),
            parent, false
        )

    protected fun getItemData(position: Int): T? =
        if (position in 0 until itemCount) items[position] else null

    fun updateData(newItems: List<T>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    fun insertData(data: List<T>) {
        val oldPosition = itemCount
        items.addAll(data)
        val newPosition = itemCount
        if (newPosition > oldPosition) notifyItemRangeInserted(
            oldPosition,
            newPosition - 1
        )
    }

}
