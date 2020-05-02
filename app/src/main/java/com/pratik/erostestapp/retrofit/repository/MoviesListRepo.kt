package com.pratik.erostestapp.retrofit.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pratik.erostestapp.model.MoviesList
import com.pratik.erostestapp.retrofit.ApiRequest
import com.pratik.erostestapp.retrofit.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesListRepo {

    private var apiRequest: ApiRequest =
        RetrofitBuilder.retrofitInstance!!.create(ApiRequest::class.java)

    fun getMoviesList(
        apiKey: String?,
        pageNumber: Int
    ): MutableLiveData<MoviesList> {
        val data: MutableLiveData<MoviesList> = MutableLiveData()
        apiRequest.getMoviesList(apiKey, pageNumber)
            ?.enqueue(object : Callback<MoviesList?> {
                override fun onResponse(
                    call: Call<MoviesList?>,
                    response: Response<MoviesList?>
                ) {
                    if (response.body() != null) {
                        data.value = response.body()
                    }
                }

                override fun onFailure(
                    call: Call<MoviesList?>,
                    t: Throwable
                ) {
                    Log.d("Pratik",t.cause?.message)
                    data.value = null
                }
            })
        return data
    }

}