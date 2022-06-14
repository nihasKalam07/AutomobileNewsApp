package com.nihaskalam.automobilenewsapp.ui

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * Data binding adapter to load image from the news.
 *
 * @param view Image view on which the imege to be shown
 * @param url image Url
 * @param placeholder placeholder for imageview
 * @param error image to be shown in case of image loading fail
 */
@BindingAdapter("imageUrl", "placeholder", "error", requireAll = false)
fun loadImage(view: ImageView, url: String, placeholder: Drawable?, error: Drawable?) {
    Glide.with(view.context)
        .load(url)
        .centerCrop()
        .placeholder(placeholder)
        .error(error)
        .into(view)
}