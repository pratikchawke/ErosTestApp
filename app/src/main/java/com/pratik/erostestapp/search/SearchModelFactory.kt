package com.pratik.erostestapp.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SearchModelFactory(val query : String) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val searchViewModel = SearchViewModel(query) as T
        return searchViewModel
    }
}