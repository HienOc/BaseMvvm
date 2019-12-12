package com.adnet.testmvvm.ui.adapter

import androidx.recyclerview.widget.DiffUtil

open class BaseDiffUtil<T>  : DiffUtil.ItemCallback<T>(){
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
