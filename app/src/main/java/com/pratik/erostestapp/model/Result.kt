package com.pratik.erostestapp.model

import android.os.Parcel
import android.os.Parcelable
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.pratik.erostestapp.R
import com.pratik.erostestapp.utils.AppConstants

data class Result(
    val adult: Boolean,
    val backdrop_path: String?,
    val id: Int,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Double,
    val poster_path: String?,
    val release_date: String?,
    val title: String?,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int,
    @Bindable var favourite: Boolean = false
) : BaseObservable(), Parcelable {


    constructor(parcel: Parcel) : this(
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }


    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<Result> {
            override fun createFromParcel(parcel: Parcel) = Result(parcel)
            override fun newArray(size: Int) = arrayOfNulls<Result>(size)
        }

        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(view: ImageView, image_path: String?) {
            Glide.with(view.context)
                .load(AppConstants.BASE_IMAGE_URL + image_path)
                .into(view)
        }
        @JvmStatic
        @BindingAdapter("description")
        fun showMovieDescription(view: TextView, description: String?) {
            view.text = description
        }
        @JvmStatic
        @BindingAdapter("favIcon")
        fun favIcon(view: ImageView, flag: Boolean?) {
            if (flag!!) {
                view.setImageResource(R.drawable.ic_heart_selected)
            } else {
                view.setImageResource(R.drawable.ic_heart)
            }
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (adult) 1 else 0)
        parcel.writeString(backdrop_path)
        parcel.writeInt(id)
        parcel.writeString(original_language)
        parcel.writeString(original_title)
        parcel.writeString(overview)
        parcel.writeDouble(popularity)
        parcel.writeString(poster_path)
        parcel.writeString(release_date)
        parcel.writeString(title)
        parcel.writeByte(if (video) 1 else 0)
        parcel.writeDouble(vote_average)
        parcel.writeInt(vote_count)
        parcel.writeByte(if (favourite) 1 else 0)
    }
}