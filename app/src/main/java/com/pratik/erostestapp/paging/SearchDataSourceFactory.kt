package com.pratik.erostestapp.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.pratik.erostestapp.model.Result

class SearchDataSourceFactory(val query: String) : DataSource.Factory<Int, Result>() {
    val searchLiveDataSource = MutableLiveData<PageKeyedDataSource<Int, Result>>()
    var searchDataSource : SearchDataSource? = null

    override fun create(): DataSource<Int, Result> {
        searchDataSource = SearchDataSource(query)
        searchLiveDataSource.postValue(searchDataSource)
        return searchDataSource!!
    }
}