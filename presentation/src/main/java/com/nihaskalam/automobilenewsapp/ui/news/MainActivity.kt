package com.nihaskalam.automobilenewsapp.ui.news

import android.os.Bundle
import com.nihaskalam.automobilenewsapp.ui.base.BaseActivity
import com.nihaskalam.automobilenewsapp.ui.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main activity to hold both news list and news details fragments.
 *
 */
@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}