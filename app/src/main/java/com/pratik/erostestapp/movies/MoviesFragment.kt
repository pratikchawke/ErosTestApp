package com.pratik.erostestapp.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pratik.erostestapp.R
import com.pratik.erostestapp.movies.adapter.MoviesListAdapter
import com.pratik.erostestapp.movies.model.MoviesList
import com.pratik.erostestapp.movies.model.Result

class MoviesFragment : Fragment() {

    private lateinit var movieViewModel: MoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //todo - show loader
        movieViewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_movies, container, false)

        val recyclerView = root.findViewById<RecyclerView>(R.id.moviesRV)
        val recyclerViewManager = GridLayoutManager(context, 3)
        recyclerView.layoutManager = recyclerViewManager
        val moviesListAdapter = MoviesListAdapter(context)
        recyclerView.adapter = moviesListAdapter

        movieViewModel.movieDataPagedList.observe(viewLifecycleOwner,
            Observer{
                    list ->
                moviesListAdapter.submitList(list)
                moviesListAdapter.notifyDataSetChanged()
            })

        return root
    }
}
