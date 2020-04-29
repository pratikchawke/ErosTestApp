package com.pratik.erostestapp.movies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
    private  var moviesResultList : ArrayList<Result> = ArrayList()
    private lateinit var moviesList: MoviesList

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
        val moviesListAdapter = MoviesListAdapter(context,moviesResultList)
        recyclerView.adapter = moviesListAdapter

        movieViewModel.moviesArrayList.observe(viewLifecycleOwner, Observer { list ->
           Log.d("Pratik","Observing data on movieViewModel")
            getMoviesListResult(list)
            moviesListAdapter.notifyDataSetChanged()
            //todo -  dismiss loader
        })
        return root
    }

    private fun getMoviesListResult(list : MoviesList){
        Log.d("Pratik","getMovieListResult : "+list)
        if (list !=null){
            // todo - validate response code and messages
            moviesResultList.addAll(list.results)
        }else{
            Toast.makeText(context, " Error in Response !!!",Toast.LENGTH_SHORT).show()
        }
    }
}
