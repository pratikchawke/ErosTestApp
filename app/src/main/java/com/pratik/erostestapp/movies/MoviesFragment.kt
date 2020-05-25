package com.pratik.erostestapp.movies

import android.content.Intent
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
import com.pratik.erostestapp.MainActivity.Companion.favouriteDataList
import com.pratik.erostestapp.MainActivity.Companion.movieViewModel
import com.pratik.erostestapp.MainActivity.Companion.resultList
import com.pratik.erostestapp.MovieDetailsActivity
import com.pratik.erostestapp.R
import com.pratik.erostestapp.databinding.FragmentMoviesBinding
import com.pratik.erostestapp.databinding.MovieItemViewBinding
import com.pratik.erostestapp.listener.OnMovieItemClickListener
import com.pratik.erostestapp.model.Result
import com.pratik.erostestapp.movies.adapter.MoviesListAdapter


class MoviesFragment : Fragment(), OnMovieItemClickListener {

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
        val moviesListAdapter = MoviesListAdapter( this)
        recyclerView?.adapter = moviesListAdapter

        if (movieViewModel == null)
            movieViewModel =
                ViewModelProviders.of(this, MovieModelFactory()).get(MoviesViewModel::class.java)

        movieViewModel?.movieDataPagedList?.observe(viewLifecycleOwner,
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
                val bundle = Bundle()
                bundle.putString("query",query)
                val navigationController =
                    Navigation.findNavController(fragmentMoviesBinding.root!!)
                navigationController.navigate(R.id.action_navigation_movies_to_navigation_search,bundle)
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

    override fun onFavouriteButtonClicked(movieResult: Result?, itemBinding: MovieItemViewBinding) {
        if (movieResult!!.favourite) {
            movieResult!!.favourite = false
            itemBinding.favouriteBtn.setImageResource(R.drawable.ic_heart)
            resultList.remove(movieResult)
        } else {
            movieResult!!.favourite = true
            itemBinding.favouriteBtn.setImageResource(R.drawable.ic_heart_selected)
            resultList.add(movieResult)
        }
        favouriteDataList.value = MainActivity.resultList
    }

    override fun onItemClicked(movieResult: Result?) {
        val intent = Intent(context, MovieDetailsActivity::class.java)
        intent.putExtra("data", movieResult)
        context?.startActivity(intent)
    }

}
