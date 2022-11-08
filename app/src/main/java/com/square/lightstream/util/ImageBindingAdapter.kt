package com.square.lightstream.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("android:imageUrl")
fun loadImageIntoView(imageView: ImageView, url: String?) {
    url?.let {
        Glide.with(imageView)
            .load(url)
            .into(imageView)
    }
}