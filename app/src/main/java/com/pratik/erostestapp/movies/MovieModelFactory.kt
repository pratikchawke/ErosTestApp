package com.pratik.erostestapp.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pratik.erostestapp.listener.LoadingListener

class MovieModelFactory(
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val movieViewModel = MoviesViewModel() as T
        return movieViewModel
    }
}