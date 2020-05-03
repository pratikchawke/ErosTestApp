package com.pratik.erostestapp.model

import android.os.Parcelable
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.pratik.erostestapp.R
import com.pratik.erostestapp.utils.AppConstants

data class MoviesList(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)

