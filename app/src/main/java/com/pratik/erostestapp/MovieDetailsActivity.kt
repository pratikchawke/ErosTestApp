package com.pratik.erostestapp

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.pratik.erostestapp.databinding.ActivityMovieDetailsBinding
import com.pratik.erostestapp.model.Result

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details)
        getIntentData()
    }

    private fun getIntentData() {
        val movieDetail = intent.getParcelableExtra<Result>("data")
        binding.detail = movieDetail
        supportActionBar?.title = movieDetail.title

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}