package com.pratik.erostestapp.favourite

import android.os.Bundle
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pratik.erostestapp.R
import com.pratik.erostestapp.databinding.FragmentFavouriteBinding
import com.pratik.erostestapp.favorite.adapter.FavouriteListAdapter
import com.pratik.erostestapp.model.Result
import java.util.*
import kotlin.collections.ArrayList

class FavouriteFragment : Fragment() {

    private lateinit var favouriteViewModel: FavouriteViewModel
    private lateinit var favouriteBinding: FragmentFavouriteBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favouriteViewModel = ViewModelProviders.of(this).get(FavouriteViewModel::class.java)
        favouriteBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_favourite, container, false)
        favouriteViewModel.favouriteData!!.observe(viewLifecycleOwner, Observer { result ->
            getObserverDataAndSetAdapter(result!!)
        })
        return favouriteBinding.root
    }

    fun getObserverDataAndSetAdapter(result: ArrayList<Result>) {
        val root = favouriteBinding.root
        val recyclerView = root.findViewById<RecyclerView>(R.id.faouriteRV)
        val recyclerViewManager = LinearLayoutManager(context)
        recyclerView.layoutManager = recyclerViewManager
        val favouriteListAdapter = FavouriteListAdapter(context,result)
        recyclerView.adapter = favouriteListAdapter
    }

}
