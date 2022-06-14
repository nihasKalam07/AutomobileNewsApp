package com.nihaskalam.automobilenewsapp.ui.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

/**
 * Base fragment to provide common functionalities
 */
abstract class BaseFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        observeData()
    }

    /**
     * Initializing UI components
     */
    abstract fun initUi()

    /**
     * Method to observe livedata of viewmodel
     */
    abstract fun observeData()
}