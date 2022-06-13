package com.nihaskalam.automobilenewsapp.domain.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

/**
 * Data class to store an item of a Newsfeed
 */
@Keep
@Parcelize
data class Data(
    val author: String,
    val content: String,
    val date: String,
    val id: String,
    val imageUrl: String,
    val readMoreUrl: String,
    val time: String,
    val title: String,
    val url: String
) : Parcelable