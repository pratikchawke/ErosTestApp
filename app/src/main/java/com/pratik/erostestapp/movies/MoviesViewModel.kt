package com.pratik.erostestapp.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pratik.erostestapp.movies.model.MoviesList
import com.pratik.erostestapp.retrofit.repository.MoviesListRepo
import com.pratik.erostestapp.utils.AppConstants

class MoviesViewModel : ViewModel() {
    private  var moviesRepo : MoviesListRepo = MoviesListRepo()
    private  var moviesResponseMutableLiveData : MutableLiveData<MoviesList>

    init {
        moviesResponseMutableLiveData =  moviesRepo.getMoviesList(AppConstants.API_KEY, AppConstants.INITIAL_INDEX)
    }

    val moviesArrayList: LiveData<MoviesList> = moviesResponseMutableLiveData
}