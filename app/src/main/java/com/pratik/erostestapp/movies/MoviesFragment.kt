package com.pratik.erostestapp.movies

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pratik.erostestapp.MainActivity
import com.pratik.erostestapp.R
import com.pratik.erostestapp.databinding.FragmentMoviesBinding
import com.pratik.erostestapp.movies.adapter.MoviesListAdapter


class MoviesFragment : Fragment() {

    private lateinit var fragmentMoviesBinding: FragmentMoviesBinding
    private val handler = Handler()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        fragmentMoviesBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_movies, container, false)

        val recyclerView = fragmentMoviesBinding.root?.findViewById<RecyclerView>(R.id.moviesRV)
        val orientation = this.resources.configuration.orientation

        var recyclerViewManager: GridLayoutManager
        if (orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
            recyclerViewManager = GridLayoutManager(context, 3)
        else
            recyclerViewManager = GridLayoutManager(context, 4)

        recyclerView?.layoutManager = recyclerViewManager
        val moviesListAdapter = MoviesListAdapter(context)
        recyclerView?.adapter = moviesListAdapter

        var movieViewModel =
            ViewModelProviders.of(this, MovieModelFactory()).get(MoviesViewModel::class.java)
        movieViewModel.movieDataPagedList.observe(viewLifecycleOwner,
            Observer { list ->
                moviesListAdapter.submitList(list)
                moviesListAdapter.notifyDataSetChanged()
            })

        return fragmentMoviesBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.searchview, menu)
        val searchItem = menu.findItem(R.id.action_search)
        var searchView = searchItem.actionView as SearchView
        searchView.isSubmitButtonEnabled = true
        searchView.queryHint = "Enter movie name"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                MainActivity.query = query
                val navigationController = Navigation.findNavController(fragmentMoviesBinding.root!!)
                navigationController.navigate(R.id.action_navigation_movies_to_navigation_search)
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return if (id == R.id.action_search) {
            true
        } else super.onOptionsItemSelected(item)
    }

}
