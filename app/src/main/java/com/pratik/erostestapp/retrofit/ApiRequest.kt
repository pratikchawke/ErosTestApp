package com.pratik.erostestapp.retrofit

import com.pratik.erostestapp.movies.model.MoviesList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequest {
    @GET("3/movie/top_rated?")
    fun getMoviesList(
        @Query("api_key") apiKey: String?,
        @Query("page") page: Int
    ): Call<MoviesList?>?
}