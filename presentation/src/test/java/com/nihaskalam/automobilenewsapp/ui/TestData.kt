package com.nihaskalam.automobilenewsapp.ui

import com.nihaskalam.automobilenewsapp.domain.model.Data
import com.nihaskalam.automobilenewsapp.domain.model.NewsFeed


object TestData {
    private val data = Data(
        author = "",
        content = "",
        date = "",
        id = "",
        imageUrl = "",
        readMoreUrl = "",
        time = "",
        title = "",
        url = ""
    )
    val testData = NewsFeed(category = "Auto", data = listOf(data), success = true)
}