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

@HiltViewModel
class NewsListViewModel @Inject constructor(private val getNews: GetNewsUseCase): BaseViewModel() {

    private val _newsFeedList = MutableLiveData<NetworkResult<NewsFeed>>()
    val newsFeedList: LiveData<NetworkResult<NewsFeed>> = _newsFeedList

    init {
        getNewsFeeds()
    }

    /**
     * Getting news feed list from remote
     */
    fun getNewsFeeds() {
        viewModelScope.launch {
            getNews().collect {
                _newsFeedList.value = it
            }
        }
    }
}