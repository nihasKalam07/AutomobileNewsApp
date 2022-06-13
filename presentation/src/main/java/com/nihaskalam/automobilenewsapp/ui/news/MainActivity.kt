package com.nihaskalam.automobilenewsapp.ui.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.nihaskalam.automobilenewsapp.ui.R
import com.nihaskalam.automobilenewsapp.ui.base.BaseActivity
import com.nihaskalam.automobilenewsapp.ui.databinding.ActivityMainBinding
import com.nihaskalam.automobilenewsapp.ui.news.list.NewsListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}