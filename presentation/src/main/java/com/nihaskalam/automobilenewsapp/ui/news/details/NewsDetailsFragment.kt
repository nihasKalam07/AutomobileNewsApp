package com.nihaskalam.automobilenewsapp.ui.news.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.nihaskalam.automobilenewsapp.ui.R
import com.nihaskalam.automobilenewsapp.ui.base.BaseFragment
import com.nihaskalam.automobilenewsapp.ui.databinding.FragmentNewsDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Fragment to display details of a Newsfeed
 */
@AndroidEntryPoint
class NewsDetailsFragment : BaseFragment() {
    private lateinit var binding: FragmentNewsDetailsBinding

    /**
     * Arguments getting vis safe args
     */
    val args: NewsDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentNewsDetailsBinding.inflate(inflater, container, false).let {
        binding = it
        with(it) {
            header = getString(R.string.ui_label_details_page_header)
            item = args.newsItem
            sharedElementEnterTransition =
                TransitionInflater.from(context).inflateTransition(android.R.transition.move)
            root
        }
    }

    override fun initUi() {
        binding.upButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun observeData() {
    }
}