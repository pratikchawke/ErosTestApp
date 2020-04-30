package com.pratik.erostestapp.movies.adapter

import android.content.ClipData.Item
import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pratik.erostestapp.MovieDetailsActivity
import com.pratik.erostestapp.R
import com.pratik.erostestapp.movies.model.MoviesList
import com.pratik.erostestapp.movies.model.Result
import com.pratik.erostestapp.utils.AppConstants


class MoviesListAdapter(private val context: Context?) :
    PagedListAdapter<Result, MoviesListAdapter.ItemViewHolder>(DIFF_CALLBACK) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val movieResult = getItem(position)
        if (movieResult != null)
            Glide.with(context!!).load(AppConstants.BASE_IMAGE_URL + movieResult.poster_path)
                .into(holder.imageView)
        holder.imageView.setOnClickListener {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra("data", movieResult.toString())
            context!!.startActivity(intent)
        }
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById<ImageView>(R.id.imageView)
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<Result> =
            object : DiffUtil.ItemCallback<Result>() {
                override fun areItemsTheSame(
                    oldItem: Result,
                    newItem: Result
                ): Boolean {
                    return oldItem.id === newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: Result,
                    newItem: Result
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}