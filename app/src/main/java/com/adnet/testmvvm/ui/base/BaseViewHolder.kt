package com.adnet.testmvvm.ui.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class BaseViewHolder<T, VB : ViewDataBinding>(
    val binding: VB
) : RecyclerView.ViewHolder(binding.root) {

    private var itemData: T? = null
    private var itemPosition: Int = -1

    open fun onBindData(itemData: T) {
        this.itemData = itemData
    }
    open fun onBindData(itemPosition: Int, itemData: T) {
        this.itemPosition = itemPosition
        this.itemData = itemData
    }
}
