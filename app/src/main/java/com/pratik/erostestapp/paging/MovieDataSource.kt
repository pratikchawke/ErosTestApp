package com.pratik.erostestapp.paging

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.pratik.erostestapp.model.MoviesList
import com.pratik.erostestapp.model.Result
import com.pratik.erostestapp.retrofit.ApiRequest
import com.pratik.erostestapp.retrofit.RetrofitBuilder
import com.pratik.erostestapp.utils.AppConstants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDataSource : PageKeyedDataSource<Int, Result>() {

    private var apiRequest: ApiRequest =
        RetrofitBuilder.retrofitInstance!!.create(ApiRequest::class.java)
    var NEXT_INDEX = 1;

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Result>) {
        apiRequest.getMoviesList(AppConstants.API_KEY, AppConstants.INITIAL_INDEX)
            ?.enqueue(object : Callback<MoviesList?> {
                override fun onResponse(call: Call<MoviesList?>, response: Response<MoviesList?>) {
                    if (response.body() != null) {
                        NEXT_INDEX = AppConstants.INITIAL_INDEX+1
                       callback.onResult( response.body()!!.results,null,NEXT_INDEX)
                    }
                }
                override fun onFailure(call: Call<MoviesList?>, t: Throwable) {
                    Log.d("Pratik", t.cause?.message)
                }
            })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {
        apiRequest.getMoviesList(AppConstants.API_KEY, NEXT_INDEX)
            ?.enqueue(object : Callback<MoviesList?> {
                override fun onResponse(call: Call<MoviesList?>, response: Response<MoviesList?>) {
                    if (response.body() != null) {
                        NEXT_INDEX += 1
                        callback.onResult(response.body()!!.results,NEXT_INDEX )
                    }
                }
                override fun onFailure(call: Call<MoviesList?>, t: Throwable) {
                    Log.d("Pratik", t.cause?.message)
                }
            })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {
        TODO("Not yet implemented")
    }
}