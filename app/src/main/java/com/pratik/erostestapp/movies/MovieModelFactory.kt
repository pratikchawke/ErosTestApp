package com.pratik.erostestapp.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pratik.erostestapp.listener.LoadingListener

class MovieModelFactory(
    private val listener: LoadingListener
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MoviesViewModel(listener) as T
    }
}