package com.pratik.erostestapp.paging

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.pratik.erostestapp.listener.LoadingListener
import com.pratik.erostestapp.model.MoviesList
import com.pratik.erostestapp.model.Result
import com.pratik.erostestapp.movies.MoviesFragment
import com.pratik.erostestapp.retrofit.ApiRequest
import com.pratik.erostestapp.retrofit.RetrofitBuilder
import com.pratik.erostestapp.utils.AppConstants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDataSource() :
    PageKeyedDataSource<Int, Result>() {
    private var apiRequest: ApiRequest =
        RetrofitBuilder.retrofitInstance!!.create(ApiRequest::class.java)
    var NEXT_INDEX = 2;

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Result>
    ) {
        getInitialMovieList(callback)
    }

    private fun getInitialMovieList(callback: LoadInitialCallback<Int, Result>) {
        MoviesFragment.loader.showLoading()
        apiRequest.getMoviesList(AppConstants.API_KEY, AppConstants.INITIAL_INDEX)
            ?.enqueue(object : Callback<MoviesList?> {
                override fun onResponse(call: Call<MoviesList?>, response: Response<MoviesList?>) {
                    if (response.body() != null) {
                        MoviesFragment.loader.dismissLoading()
                        callback.onResult(response.body()!!.results, null, NEXT_INDEX)
                    }
                }

                override fun onFailure(call: Call<MoviesList?>, t: Throwable) {
                    Log.d("Pratik", t.cause?.message)
                    MoviesFragment.loader.dismissLoading()
                }
            })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {
        Log.d("MovieDataSource", "loadAfter $NEXT_INDEX !!!")
        MoviesFragment.loader.showLoading()
        apiRequest.getMoviesList(AppConstants.API_KEY, NEXT_INDEX)
            ?.enqueue(object : Callback<MoviesList?> {
                override fun onResponse(call: Call<MoviesList?>, response: Response<MoviesList?>) {
                    if (response.body() != null) {
                        MoviesFragment.loader.dismissLoading()
                        callback.onResult(response.body()!!.results, NEXT_INDEX)
                        NEXT_INDEX += 1
                    }
                }

                override fun onFailure(call: Call<MoviesList?>, t: Throwable) {
                    Log.d("Pratik", t.cause?.message)
                    MoviesFragment.loader.dismissLoading()
                }
            })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {
        TODO("Not yet implemented")
    }
}