package com.pratik.erostestapp.search

import android.content.pm.ActivityInfo
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
import com.pratik.erostestapp.MainActivity
import com.pratik.erostestapp.R
import com.pratik.erostestapp.databinding.FragmentMoviesBinding
import com.pratik.erostestapp.movies.adapter.SearchListAdapter

class SearchFragment : Fragment() {

    private lateinit var fragmentMoviesBinding: FragmentMoviesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        fragmentMoviesBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_movies,
            container, false
        )
        (activity as MainActivity).supportActionBar?.title = MainActivity.query
        val recyclerView = fragmentMoviesBinding.root?.findViewById<RecyclerView>(R.id.moviesRV)
        val orientation = this.resources.configuration.orientation

        var recyclerViewManager: GridLayoutManager
        if (orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
            recyclerViewManager = GridLayoutManager(context, 3)
        else
            recyclerViewManager = GridLayoutManager(context, 4)

        recyclerView?.layoutManager = recyclerViewManager
        val moviesListAdapter = SearchListAdapter(context)
        recyclerView?.adapter = moviesListAdapter


        var searchViewModel =
            ViewModelProviders.of(this, SearchModelFactory())
                .get(SearchViewModel::class.java)
        searchViewModel.searchDataPagedList!!.observe(viewLifecycleOwner,
            Observer { list ->
                moviesListAdapter.submitList(list)
                moviesListAdapter.notifyDataSetChanged()
            })

        return fragmentMoviesBinding.root
    }
}