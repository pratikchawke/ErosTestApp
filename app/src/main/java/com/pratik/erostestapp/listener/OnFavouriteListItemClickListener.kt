package com.pratik.erostestapp.listener

import com.pratik.erostestapp.model.Result

interface OnFavouriteListItemClickListener {
    fun onItemRemove(movieResult : Result?,position:Int)
    fun onItemClicked(movieResult : Result?)
}