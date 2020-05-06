package com.pratik.erostestapp.favourite.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.pratik.erostestapp.R
import com.pratik.erostestapp.databinding.FavouriteItemViewBinding
import com.pratik.erostestapp.listener.OnFavouriteListItemClickListener
import com.pratik.erostestapp.model.Result


class FavouriteListAdapter(
    private val context: Context?,
    private var movieList: ArrayList<Result>,
    private val onFavouriteListClickListener: OnFavouriteListItemClickListener
) : RecyclerView.Adapter<FavouriteListAdapter.ItemViewHolder>() {

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

    fun submitList(movieList: ArrayList<Result>) {
        this.movieList = movieList
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val movieResult = movieList.get(position)
        holder.itemBinding.favouriteList = movieResult
        holder.itemBinding.favouriteItemImageVIew.setOnClickListener {
            onFavouriteListClickListener.onItemClicked(movieResult)
        }
        holder.itemBinding.removeFavourateBtn.setOnClickListener {
            onFavouriteListClickListener.onItemRemove(movieResult, position)
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