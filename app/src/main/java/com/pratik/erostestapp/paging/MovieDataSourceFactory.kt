package com.pratik.erostestapp.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.pratik.erostestapp.model.Result

class MovieDataSourceFactory() :
    DataSource.Factory<Int, Result>() {

    val movieLiveDataSource = MutableLiveData<PageKeyedDataSource<Int, Result>>()
     var movieDataSource : MovieDataSource? = null


    override fun create(): DataSource<Int, Result> {
        movieDataSource = MovieDataSource()
        movieLiveDataSource.postValue(movieDataSource)
        return movieDataSource!!
    }
}