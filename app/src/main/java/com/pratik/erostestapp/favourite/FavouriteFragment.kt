package com.pratik.erostestapp.favourite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pratik.erostestapp.MainActivity
import com.pratik.erostestapp.MovieDetailsActivity
import com.pratik.erostestapp.R
import com.pratik.erostestapp.databinding.FragmentFavouriteBinding
import com.pratik.erostestapp.favourite.adapter.FavouriteListAdapter
import com.pratik.erostestapp.listener.OnFavouriteListItemClickListener
import com.pratik.erostestapp.model.Result

class FavouriteFragment : Fragment(), OnFavouriteListItemClickListener {
    private lateinit var favouriteBinding: FragmentFavouriteBinding
    private lateinit var favouriteListAdapter: FavouriteListAdapter
    private var result: ArrayList<Result> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        favouriteViewModel = ViewModelProvider(this).get(FavouriteViewModel::class.java)
        favouriteBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_favourite, container, false)

        val root = favouriteBinding.root
        val recyclerView = root.findViewById<RecyclerView>(R.id.faouriteRV)
        val recyclerViewManager = LinearLayoutManager(context)
        recyclerView.layoutManager = recyclerViewManager
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                recyclerViewManager.orientation
            )
        )
        favouriteListAdapter = FavouriteListAdapter(context, result, this)
        recyclerView.adapter = favouriteListAdapter
        MainActivity.favouriteDataList!!.observe(viewLifecycleOwner, Observer { result ->
            getObserverDataAndSetAdapter(result!!)
        })
        return favouriteBinding.root
    }

    private fun getObserverDataAndSetAdapter(result: ArrayList<Result>) {
        this.result = result
        favouriteListAdapter.submitList(this.result)
        favouriteListAdapter.notifyDataSetChanged()
    }

    override fun onItemRemove(movieResult: Result?, position: Int) {
        movieResult!!.favourite = false
        MainActivity.resultList.remove(movieResult)
        MainActivity.favouriteDataList.value = MainActivity.resultList
        favouriteListAdapter.notifyItemRemoved(position)
    }

    override fun onItemClicked(movieResult: Result?) {
        val intent = Intent(context, MovieDetailsActivity::class.java)
        intent.putExtra("data", movieResult)
        context?.startActivity(intent)
    }

}
