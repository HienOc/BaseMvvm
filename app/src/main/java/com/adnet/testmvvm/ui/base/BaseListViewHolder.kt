package com.adnet.testmvvm.ui.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class BaseListViewHolder<T, VB : ViewDataBinding>(
    val binding: VB
) : RecyclerView.ViewHolder(binding.root)
