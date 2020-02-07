package com.adnet.testmvvm.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.adnet.testmvvm.R
import com.bumptech.glide.Glide

object ImageBindingAdapter {
    @JvmStatic
    @BindingAdapter("android:url")
    fun setImageUrl(view: ImageView, url: String) {
        Glide
            .with(view.context)
            .load(url)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .into(view)
    }
}
