package com.nihaskalam.automobilenewsapp.ui.news.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.nihaskalam.automobilenewsapp.domain.model.Data
import com.nihaskalam.automobilenewsapp.domain.model.NetworkResult
import com.nihaskalam.automobilenewsapp.ui.R
import com.nihaskalam.automobilenewsapp.ui.base.BaseFragment
import com.nihaskalam.automobilenewsapp.ui.databinding.FragmentListNewsBinding
import com.nihaskalam.automobilenewsapp.ui.showToasts
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsListFragment : BaseFragment() {
    private val viewModel: NewsListViewModel by viewModels()

    private lateinit var binding: FragmentListNewsBinding
    private lateinit var adapter: NewsAdapter

    companion object {
        fun newInstance() = NewsListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentListNewsBinding.inflate(inflater, container, false).let {
        binding = it
        with(it) {
            header = getString(R.string.ui_label_automobile_news)
            root
        }
    }

    override fun initUi() {
        binding.let {
            adapter = NewsAdapter(arrayListOf(), onNewsItemClick)
            it.newsRv.adapter = adapter
            it.swipeRefreshLayout
            it.swipeRefreshLayout.setOnRefreshListener {
                viewModel.getNewsFeeds()
            }
        }
    }

    override fun observeData() {
        viewModel.newsFeedList.observe(viewLifecycleOwner) { result ->

            binding.swipeRefreshLayout.isRefreshing = when (result) {
                is NetworkResult.ApiSuccess -> {
                    result.newsList.let {
                        adapter.update(it.data.take(10))
                    }
                    false
                }
                is NetworkResult.ApiError -> {
                    result.message?.let {
                        context?.showToasts(it)
                    }
                    false
                }
                is NetworkResult.ApiException -> {
                    result.e.message?.let {
                        context?.showToasts(it)
                    }
                    false
                }
                is NetworkResult.ApiLoading -> true
            }
        }
    }

    private val onNewsItemClick: (newsItem: Data, view: View) -> Unit =
        { newsItem, view ->
            val directions = NewsListFragmentDirections.actionNewsToDetails(newsItem)
            val extras = FragmentNavigatorExtras(
                view to newsItem.imageUrl
            )
            findNavController().navigate(directions, extras)
        }
}