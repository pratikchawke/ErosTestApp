package com.pratik.erostestapp.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pratik.erostestapp.R
import com.pratik.erostestapp.databinding.FragmentFavouriteBinding
import com.pratik.erostestapp.favourite.adapter.FavouriteListAdapter
import com.pratik.erostestapp.model.Result

class FavouriteFragment : Fragment() {

    private lateinit var favouriteViewModel: FavouriteViewModel
    private lateinit var favouriteBinding: FragmentFavouriteBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favouriteViewModel = ViewModelProvider(this).get(FavouriteViewModel::class.java)
        favouriteBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_favourite, container, false)
        favouriteViewModel.favouriteData!!.observe(viewLifecycleOwner, Observer { result ->
            getObserverDataAndSetAdapter(result!!)
        })
        return favouriteBinding.root
    }

    private fun getObserverDataAndSetAdapter(result: ArrayList<Result>) {
        val root = favouriteBinding.root
        val recyclerView = root.findViewById<RecyclerView>(R.id.faouriteRV)
        val recyclerViewManager = LinearLayoutManager(context)
        recyclerView.layoutManager = recyclerViewManager
        val favouriteListAdapter = FavouriteListAdapter(context, result)
        recyclerView.adapter = favouriteListAdapter
    }

}
