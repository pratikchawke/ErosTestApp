package com.pratik.erostestapp.movies

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pratik.erostestapp.MainActivity
import com.pratik.erostestapp.MainActivity.Companion.rootView
import com.pratik.erostestapp.R
import com.pratik.erostestapp.databinding.FragmentMoviesBinding
import com.pratik.erostestapp.listener.LoadingListener
import com.pratik.erostestapp.movies.adapter.MoviesListAdapter
import com.pratik.erostestapp.search.SearchModelFactory
import com.pratik.erostestapp.search.SearchViewModel
import com.pratik.erostestapp.utils.AppUtils.hideLoader
import com.pratik.erostestapp.utils.AppUtils.showLoader


class MoviesFragment : Fragment(), LoadingListener {

    private lateinit var fragmentMoviesBinding: FragmentMoviesBinding
    private val handler = Handler()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)
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


        if (MainActivity.isSearchedQuery) {
            var searchViewModel =
                ViewModelProviders.of(this, SearchModelFactory(this))
                    .get(SearchViewModel::class.java)
            searchViewModel.searchDataPagedList!!.observe(viewLifecycleOwner,
                Observer { list ->
                    moviesListAdapter.submitList(list)
                    moviesListAdapter.notifyDataSetChanged()
                    MainActivity.isSearchedQuery = false
                })
        } else {
            var movieViewModel =
                ViewModelProviders.of(this, MovieModelFactory(this))
                    .get(MoviesViewModel::class.java)
            movieViewModel.movieDataPagedList!!.observe(viewLifecycleOwner,
                Observer { list ->
                    moviesListAdapter.submitList(list)
                    moviesListAdapter.notifyDataSetChanged()
                })
        }

        return rootView
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.searchview, menu)
        val searchItem = menu!!.findItem(R.id.action_search)
        var searchView = searchItem.actionView as SearchView
        searchView.isSubmitButtonEnabled = true
        searchView.queryHint = "Enter movie name"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                MainActivity.query = query
                MainActivity.isSearchedQuery = true
                hideKeyboard(searchView)
                refreshView()
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

    override fun showLoading() {
        handler.post {
            showLoader(requireContext())
        }
    }

    override fun dismissLoading() {
        handler.post {
            hideLoader()
        }
    }

    fun hideKeyboard(view : View) {
        val imm = view.context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
    fun refreshView() {
        val ft: FragmentTransaction = parentFragmentManager!!.beginTransaction()
        if (Build.VERSION.SDK_INT >= 26) {
            ft.setReorderingAllowed(false)
        }
        ft.detach(this).attach(this).commit()
    }
}
