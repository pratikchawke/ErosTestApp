package com.pratik.erostestapp.favorite.adapter

import android.content.ClipData.Item
import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pratik.erostestapp.MainActivity
import com.pratik.erostestapp.MovieDetailsActivity
import com.pratik.erostestapp.R
import com.pratik.erostestapp.cache.CacheManager
import com.pratik.erostestapp.databinding.FavouriteItemViewBinding
import com.pratik.erostestapp.databinding.MovieItemViewBinding
import com.pratik.erostestapp.favourite.FavouriteViewModel
import com.pratik.erostestapp.model.MoviesList
import com.pratik.erostestapp.model.Result
import com.pratik.erostestapp.utils.AppConstants


class FavouriteListAdapter(private val context: Context?,private val movieList : ArrayList<Result>) :
    RecyclerView.Adapter<FavouriteListAdapter.ItemViewHolder>() {

    lateinit var favouriteItemBinding: FavouriteItemViewBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        favouriteItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.favourite_item_view,
            parent,
            false
        )
        return ItemViewHolder(favouriteItemBinding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val movieResult = movieList.get(position)
        holder.itemBinding.favouriteList = movieResult
        holder.itemBinding.favouriteItemImageVIew.setOnClickListener {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra("data", movieResult.toString())
            context!!.startActivity(intent)
        }
        holder.itemBinding.favouriteBtn.setOnClickListener {
            if (movieResult!!.favourite) {
                movieResult.favourite = false
            } else {
                movieResult.favourite = true
            }
        }
    }

    class ItemViewHolder(itemView: FavouriteItemViewBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        var itemBinding: FavouriteItemViewBinding = itemView
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

}