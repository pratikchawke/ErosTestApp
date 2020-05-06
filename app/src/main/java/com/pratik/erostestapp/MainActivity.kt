package com.pratik.erostestapp

import android.content.Context
import android.content.pm.ActivityInfo
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.paging.PagedList
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pratik.erostestapp.listener.LoadingListener
import com.pratik.erostestapp.model.Result
import com.pratik.erostestapp.movies.MoviesViewModel
import com.pratik.erostestapp.utils.AppUtils


class MainActivity : AppCompatActivity(), LoadingListener {
    lateinit var navController: NavController
    private val Context.isConnected: Boolean
        get() {
            return (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
                .activeNetworkInfo?.isConnected == true
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loader = this

        if (!isConnected) {
            AppUtils.showNoNetworkDialog(this)
            return
        }

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.navigation_movies, R.id.navigation_favourite)
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        NavigationUI.setupActionBarWithNavController(this@MainActivity, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
                || super.onSupportNavigateUp()
    }

    companion object {
        var favouriteDataList: MutableLiveData<ArrayList<Result>> =
            MutableLiveData<ArrayList<Result>>()
         var movieViewModel: MoviesViewModel? = null
        var resultList: ArrayList<Result> = ArrayList();
        var query: String? = null
        lateinit var loader: LoadingListener
    }

    override fun showLoading() {
        runOnUiThread { AppUtils.showLoader(this) }
    }

    override fun dismissLoading() {
        runOnUiThread { AppUtils.hideLoader() }
    }
}
