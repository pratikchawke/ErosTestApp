package com.pratik.erostestapp.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pratik.erostestapp.listener.LoadingListener

class SearchModelFactory(
    private val listener: LoadingListener
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val searchViewModel = SearchViewModel(listener) as T
        return searchViewModel
    }
}