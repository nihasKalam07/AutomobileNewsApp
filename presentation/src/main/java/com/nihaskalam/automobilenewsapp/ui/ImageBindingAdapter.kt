package com.nihaskalam.automobilenewsapp.ui

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl", "placeholder", "error", requireAll = false)
fun loadImage(view: ImageView, url: String, placeholder: Drawable?, error: Drawable?) {
    Glide.with(view.context)
        .load(url)
        .centerCrop()
        .placeholder(placeholder)
        .error(error)
        .into(view)
}