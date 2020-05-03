package com.pratik.erostestapp.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.pratik.erostestapp.MainActivity
import com.pratik.erostestapp.listener.LoadingListener
import com.pratik.erostestapp.model.Result
import com.pratik.erostestapp.paging.SearchDataSourceFactory
import com.pratik.erostestapp.utils.AppConstants


class SearchViewModel(val loader: LoadingListener) : ViewModel() {

     var searchDataPagedList: LiveData<PagedList<Result>>
     var liveDataSource: LiveData<PageKeyedDataSource<Int, Result>>

    init {
        val dataSourceFactory = SearchDataSourceFactory(MainActivity.query!!)
        liveDataSource = dataSourceFactory.searchLiveDataSource
        val config: PagedList.Config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(AppConstants.SEARCH_PAGE_SIZE)
            .build()
        searchDataPagedList = (LivePagedListBuilder(dataSourceFactory, config)).build()
    }
}