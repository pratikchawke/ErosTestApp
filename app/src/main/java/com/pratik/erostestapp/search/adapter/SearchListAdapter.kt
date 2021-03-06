package com.pratik.erostestapp.movies.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pratik.erostestapp.MainActivity
import com.pratik.erostestapp.MainActivity.Companion.resultList
import com.pratik.erostestapp.MovieDetailsActivity
import com.pratik.erostestapp.R
import com.pratik.erostestapp.databinding.MovieItemViewBinding
import com.pratik.erostestapp.model.Result


class SearchListAdapter(private val context: Context?) :
    PagedListAdapter<Result, SearchListAdapter.ItemViewHolder>(DIFF_CALLBACK) {
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

        val movieResult : Result? = getItem(position)
        holder.itemBinding.movie = movieResult

        holder.itemBinding.imageView.setOnClickListener {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra("data",  movieResult)
            context!!.startActivity(intent)
        }

        holder.itemBinding.favouriteBtn.setOnClickListener {
            if (movieResult!!.favourite) {
                movieResult!!.favourite = false
                holder.itemBinding.favouriteBtn.setImageResource(R.drawable.ic_heart)
                resultList.remove(movieResult)
            } else {
                movieResult!!.favourite = true
                holder.itemBinding.favouriteBtn.setImageResource(R.drawable.ic_heart_selected)
                resultList.add(movieResult)
            }
            MainActivity.favouriteDataList.value = resultList
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