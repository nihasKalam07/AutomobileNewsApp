package com.nihaskalam.automobilenewsapp.domain.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class NewsFeed(
    val category: String? = null,
    val `data`: List<Data>,
    val success: Boolean
): Parcelable