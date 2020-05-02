package com.pratik.erostestapp.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pratik.erostestapp.MainActivity.Companion.rootView
import com.pratik.erostestapp.R
import com.pratik.erostestapp.databinding.FragmentMoviesBinding
import com.pratik.erostestapp.movies.adapter.MoviesListAdapter

class MoviesFragment : Fragment() {

    private lateinit var movieViewModel: MoviesViewModel
    private lateinit var fragmentMoviesBinding: FragmentMoviesBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //todo - show loader
        if (rootView != null) return rootView
        movieViewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)
        fragmentMoviesBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_movies,
            container, false
        )
        rootView = fragmentMoviesBinding.root

        val recyclerView = rootView?.findViewById<RecyclerView>(R.id.moviesRV)
        val recyclerViewManager = GridLayoutManager(context, 3)
        recyclerView?.layoutManager = recyclerViewManager
        val moviesListAdapter = MoviesListAdapter(context)
        recyclerView?.adapter = moviesListAdapter

        movieViewModel.movieDataPagedList.observe(viewLifecycleOwner,
            Observer { list ->
                moviesListAdapter.submitList(list)
                moviesListAdapter.notifyDataSetChanged()
            })

        return rootView
    }
}
