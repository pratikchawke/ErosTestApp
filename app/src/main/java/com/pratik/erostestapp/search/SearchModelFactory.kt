package com.pratik.erostestapp.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SearchModelFactory : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val searchViewModel = SearchViewModel() as T
        return searchViewModel
    }
}