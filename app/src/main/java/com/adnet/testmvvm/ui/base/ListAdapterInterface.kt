package com.adnet.testmvvm.ui.base

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding

interface ListAdapterInterface {
    fun getItemLayoutResource(viewType: Int): Int
    fun getViewHolderDataBinding(parent: ViewGroup, viewType: Int): ViewDataBinding
}