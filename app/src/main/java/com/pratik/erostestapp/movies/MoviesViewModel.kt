package com.pratik.erostestapp.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.pratik.erostestapp.model.Result
import com.pratik.erostestapp.paging.MovieDataSourceFactory
import com.pratik.erostestapp.utils.AppConstants


class MoviesViewModel : ViewModel() {

     var movieDataPagedList: LiveData<PagedList<Result>>
     var liveDataSource: LiveData<PageKeyedDataSource<Int, Result>>

    init {
        val dataSourceFactory = MovieDataSourceFactory()
        liveDataSource = dataSourceFactory.movieLiveDataSource
        val config: PagedList.Config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(AppConstants.PAGE_SIZE)
            .build()
        movieDataPagedList = (LivePagedListBuilder(dataSourceFactory, config)).build()
//        moviesResponseMutableLiveData = moviesRepo.getMoviesList(AppConstants.API_KEY, AppConstants.INITIAL_INDEX)
    }

}