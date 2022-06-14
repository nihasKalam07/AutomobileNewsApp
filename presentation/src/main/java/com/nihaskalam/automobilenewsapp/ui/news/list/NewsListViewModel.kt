package com.nihaskalam.automobilenewsapp.ui.news.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nihaskalam.automobilenewsapp.domain.model.NetworkResult
import com.nihaskalam.automobilenewsapp.domain.model.NewsFeed
import com.nihaskalam.automobilenewsapp.domain.usecase.GetNewsUseCase
import com.nihaskalam.automobilenewsapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * View model for the news list screen
 *
 * @property getNews get news use case to fetch newsfeed.
 */
@HiltViewModel
class NewsListViewModel @Inject constructor(private val getNews: GetNewsUseCase) : BaseViewModel() {

    private val _newsObj = MutableLiveData<NetworkResult<NewsFeed>>()
    val newsObj: LiveData<NetworkResult<NewsFeed>> = _newsObj

    init {
        getNewsFeeds()
    }

    /**
     * Getting news feed list from remote
     */
    fun getNewsFeeds() {
        viewModelScope.launch {
            getNews().collect {
                _newsObj.value = it
            }
        }
    }
}