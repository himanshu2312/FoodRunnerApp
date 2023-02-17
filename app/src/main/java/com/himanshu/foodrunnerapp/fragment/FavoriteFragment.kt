@file:Suppress("DEPRECATION")

package com.himanshu.foodrunnerapp.fragment

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.himanshu.foodrunnerapp.R
import com.himanshu.foodrunnerapp.adapter.FavResAdapter
import com.himanshu.foodrunnerapp.datbase.ResDatabase
import com.himanshu.foodrunnerapp.datbase.RestaurantEntity


@Suppress("DEPRECATION")
class FavoriteFragment : Fragment() {

    private lateinit var progressBar: ProgressBar
    private lateinit var progressBarLayout: RelativeLayout
    private lateinit var rvFavRestaurantList: RecyclerView
    private var favResList = listOf<RestaurantEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)
        progressBar = view.findViewById(R.id.progressBar)
        progressBarLayout = view.findViewById(R.id.progressBarLayout)
        rvFavRestaurantList = view.findViewById(R.id.rvFavRestaurantList)
        progressBarLayout.visibility = View.VISIBLE
        if (activity != null) {
            progressBarLayout.visibility=View.GONE
            favResList= FavResData(activity as Context).execute().get()
            if (favResList.isEmpty()){
                Toast.makeText(
                    activity as  Context,
                    "Nothing in favorites!!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else{
                rvFavRestaurantList.adapter=FavResAdapter(activity as Context,favResList)
                rvFavRestaurantList.layoutManager = LinearLayoutManager(activity as Context)
            }
        }

        return view
    }

    class FavResData(context: Context): AsyncTask<Void,Void,List<RestaurantEntity>>(){
        val db=Room.databaseBuilder(context,ResDatabase::class.java,"res_db").build()
        @Deprecated("Deprecated in Java", ReplaceWith("db.resDao().getAllRes()"))
        override fun doInBackground(vararg p0: Void?): List<RestaurantEntity> {
            return db.resDao().getAllRes()
        }
    }

}