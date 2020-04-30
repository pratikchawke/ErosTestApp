package com.pratik.erostestapp.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.pratik.erostestapp.movies.model.Result

class MovieDataSourceFactory : DataSource.Factory<Int, Result>() {

     val movieLiveDataSource = MutableLiveData<PageKeyedDataSource<Int, Result>>()

    override fun create(): DataSource<Int, Result> {
        val movieDataSource = MovieDataSource()
        movieLiveDataSource.postValue(movieDataSource)
        return movieDataSource
    }
}