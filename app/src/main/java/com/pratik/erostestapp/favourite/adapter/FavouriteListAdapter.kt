package com.pratik.erostestapp.favourite.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.pratik.erostestapp.MainActivity
import com.pratik.erostestapp.MovieDetailsActivity
import com.pratik.erostestapp.R
import com.pratik.erostestapp.databinding.FavouriteItemViewBinding
import com.pratik.erostestapp.model.Result


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
            intent.putExtra("data", movieResult)
            context!!.startActivity(intent)
        }

        holder.itemBinding.favouriteBtn.setOnClickListener {
            MainActivity.resultList.remove(movieResult)
            MainActivity.favouriteDataList.value = MainActivity.resultList
            notifyDataSetChanged()
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