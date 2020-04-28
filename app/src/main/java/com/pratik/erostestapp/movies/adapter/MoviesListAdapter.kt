package com.pratik.erostestapp.movies.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pratik.erostestapp.R
import com.pratik.erostestapp.movies.model.Result
import com.pratik.erostestapp.utils.AppConstants

class MoviesListAdapter(private val context : Context?, private val movieList : ArrayList<Result>) :
    RecyclerView.Adapter<MoviesListAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie_item_view,parent,false))
    }

    override fun getItemCount(): Int {
       return movieList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        Glide.with(context!!).load(AppConstants.BASE_IMAGE_URL+ movieList[position].poster_path).into(holder.imageView)
        holder.title.text = movieList[position].title
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView = itemView.findViewById<ImageView>(R.id.imageView)
        var title = itemView.findViewById<TextView>(R.id.title)
    }
}