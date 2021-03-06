package com.pratik.erostestapp.retrofit

import com.pratik.erostestapp.model.MoviesList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequest {
    @GET("3/movie/top_rated?")
    fun getMoviesList(
        @Query("api_key") apiKey: String?,
        @Query("page") page: Int
    ): Call<MoviesList>

    @GET("3/search/movie?")
    fun getSearchData(
        @Query("api_key") apiKey: String?,
        @Query("page") page: Int,
        @Query("query") query: String
    ): Call<MoviesList?>?
}