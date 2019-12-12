package com.adnet.testmvvm.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.adnet.testmvvm.ui.adapter.BaseDiffUtil

abstract class BaseListAdapter<
        T,
        ViewBinding : ViewDataBinding,
        viewHolder : BaseListViewHolder<T, ViewBinding>
        > : ListAdapter<T, viewHolder>(BaseDiffUtil<T>()) {

    abstract fun getItemLayoutResource(viewType: Int): Int

    protected fun getViewHolderDataBinding(parent: ViewGroup, viewType: Int): ViewDataBinding =
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            getItemLayoutResource(viewType),
            parent, false
        )

}