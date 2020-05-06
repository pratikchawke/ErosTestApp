package com.pratik.erostestapp.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pratik.erostestapp.R
import com.pratik.erostestapp.databinding.MovieItemViewBinding
import com.pratik.erostestapp.listener.OnMovieItemClickListener
import com.pratik.erostestapp.model.Result


class MoviesListAdapter(val movieItemListener: OnMovieItemClickListener) :
    PagedListAdapter<Result, MoviesListAdapter.ItemViewHolder>(DIFF_CALLBACK) {

    lateinit var movieItemBinding: MovieItemViewBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        movieItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.movie_item_view,
            parent,
            false
        )
        return ItemViewHolder(movieItemBinding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val movieResult: Result? = getItem(position)
        holder.itemBinding.movie = movieResult

        holder.itemBinding.imageView.setOnClickListener {
            movieItemListener.onItemClicked(movieResult)
        }
        holder.itemBinding.favouriteBtn.setOnClickListener {
            movieItemListener.onFavouriteButtonClicked(movieResult, holder.itemBinding)
        }
    }

    class ItemViewHolder(itemView: MovieItemViewBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        var itemBinding: MovieItemViewBinding = itemView
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<Result> =
            object : DiffUtil.ItemCallback<Result>() {
                override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                    return oldItem.id === newItem.id
                }

                override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                    return oldItem == newItem
                }
            }
    }
}