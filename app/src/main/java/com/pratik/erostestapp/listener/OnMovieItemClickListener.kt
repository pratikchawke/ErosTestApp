package com.pratik.erostestapp.listener

import com.pratik.erostestapp.databinding.MovieItemViewBinding
import com.pratik.erostestapp.model.Result

interface OnMovieItemClickListener {
    fun onFavouriteButtonClicked(movieResult : Result?,itemBinding: MovieItemViewBinding)
    fun onItemClicked(movieResult : Result?)
}