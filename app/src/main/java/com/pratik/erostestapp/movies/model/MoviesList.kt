package com.pratik.erostestapp.movies.model

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.pratik.erostestapp.utils.AppConstants

data class MoviesList(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)

data class Result(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
){
    companion object {
        @JvmStatic @BindingAdapter("imageUrl")
        fun loadImage(view: ImageView, image_path: String?) {
            Glide.with(view.context)
                .load(AppConstants.BASE_IMAGE_URL + image_path)
                .into(view)
        }

        @JvmStatic @BindingAdapter("description")
        fun showMovieDescription(view: TextView, description: String?) {
            view.text = description
        }
    }

    override fun toString(): String {
        return "{adult:$adult, backdrop_path:'$backdrop_path', genre_ids:$genre_ids, id:$id, original_language:'$original_language', original_title:'$original_title', overview:'$overview', popularity:$popularity, poster_path:'$poster_path', release_date:'$release_date', title:'$title', video:$video, vote_average:$vote_average, vote_count:$vote_count}"
    }

}