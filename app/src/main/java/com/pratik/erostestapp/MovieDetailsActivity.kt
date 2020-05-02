package com.pratik.erostestapp

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParseException
import com.pratik.erostestapp.databinding.ActivityMovieDetailsBinding
import com.pratik.erostestapp.model.Result
import org.json.JSONObject
import java.lang.Exception

class MovieDetailsActivity : AppCompatActivity() {

    private val TAG = MovieDetailsActivity::class.java.simpleName
    private lateinit var binding: ActivityMovieDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details)
        getIntentData()
    }

    private fun getIntentData() {
        val extra = intent.getStringExtra("data")
        val gson: Gson = Gson()
        try {
            Log.d(TAG, "Extra : $extra")
            val jsonObject: JSONObject = JSONObject(extra)
            val movieDetails: Result = gson.fromJson(jsonObject.toString(), Result::class.java)
            binding.detail = movieDetails
            supportActionBar?.title = movieDetails.original_title
        } catch (ex: JsonParseException) {
            Log.d(TAG, "JsonParseException : $ex")
        } catch (ex: Exception) {
            Log.d(TAG, "Exception : $ex")
        }
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