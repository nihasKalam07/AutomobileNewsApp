package com.nihaskalam.automobilenewsapp.ui.news.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nihaskalam.automobilenewsapp.domain.model.Data
import com.nihaskalam.automobilenewsapp.ui.databinding.ItemNewsBinding
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class NewsAdapter @AssistedInject constructor(
    @Assisted val list: ArrayList<Data>,
    @Assisted val onItemClicked: (newsItem: Data, view: View) -> Unit
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    @AssistedFactory
    interface NewsAdapterFactory {
        fun create(
            list: ArrayList<Data>,
            onItemClicked: (newsItem: Data, view: View) -> Unit
        ): NewsAdapter
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNewsBinding.inflate(inflater, parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    /**
     * Method to update the data set of adapter.
     */
    fun update(newsList: List<Data>) {
        list.clear()
        list.addAll(newsList)
        notifyItemRangeChanged(0, list.size)
    }

    inner class NewsViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Data) {
            binding.newsItem = item
            binding.root.setOnClickListener {
                onItemClicked.invoke(item, binding.newsImage)
            }
        }
    }

}