package com.himanshu.foodrunnerapp.fragment

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.Volley
import com.himanshu.foodrunnerapp.R
import com.himanshu.foodrunnerapp.datbase.RestaurantEntity
import com.himanshu.foodrunnerapp.util.ConnectionManager
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.Response
import com.himanshu.foodrunnerapp.adapter.RestaurantListAdapter
import org.json.JSONException


@Suppress("DEPRECATION")
class HomeFragment : Fragment() {
    private lateinit var progressBar: ProgressBar
    private lateinit var progressBarLayout: RelativeLayout
    private lateinit var rvRestaurantList: RecyclerView
    private var resList = arrayListOf<RestaurantEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        progressBar = view.findViewById(R.id.progressBar)
        progressBarLayout = view.findViewById(R.id.progressBarLayout)
        rvRestaurantList = view.findViewById(R.id.rvRestaurantList)
        progressBarLayout.visibility = View.VISIBLE
        if (ConnectionManager().checkConnectivity(activity as Context)) {
            val queue = Volley.newRequestQueue(activity as Context)
            val url = "http://13.235.250.119/v2/restaurants/fetch_result/"
            val jsonObjectRequest = object : JsonObjectRequest(
                Method.GET, url, null,
                Response.Listener {
                     if (it.getJSONObject("data").getBoolean("success")){
                         try{
                             val resArray = it.getJSONObject("data").getJSONArray("data")
                             for (i in 0 until resArray.length()){
                                 val resObj=resArray.getJSONObject(i)
                                 val res=RestaurantEntity(
                                     resObj.getString("id"),
                                     resObj.getString("name"),
                                     resObj.getString("rating"),
                                     resObj.getString("cost_for_one"),
                                     resObj.getString("image_url")
                                 )
                                 resList.add(res)
                                 progressBarLayout.visibility=View.GONE
                                 rvRestaurantList.adapter=RestaurantListAdapter(activity as Context,resList)
                                 rvRestaurantList.layoutManager=LinearLayoutManager(activity as Context)
                             }
                         }catch (e: JSONException){
                             Toast.makeText(
                                 activity as Context,
                                 "Json error occurred",
                                 Toast.LENGTH_SHORT
                             ).show()
                         }
                     }else{
                         Toast.makeText(
                             activity as Context,
                             "Error 404!!",
                             Toast.LENGTH_SHORT
                         ).show()
                     }
                },
                Response.ErrorListener {

                    if (activity != null) {
                        Toast.makeText(
                            activity as Context,
                            "Volley error occurred!! $it",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            ) {
                override fun getHeaders(): MutableMap<String, String> {
                    val headers = HashMap<String, String>()
                    headers["Content-Type"] = "application/json"
                    headers["token"] = "56e490debc1169"
                    return headers
                }
            }
            queue.add(jsonObjectRequest)


        } else {
            val dialog = AlertDialog.Builder(context as Context)
            dialog.setMessage("Error")
            dialog.setNegativeButton("Exit") { text, listener ->
                ActivityCompat.finishAffinity(activity as Activity)
            }
            dialog.setPositiveButton("Open Settings") { text, listener ->
                val intent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
                startActivity(intent)
                activity?.finish()
            }
            dialog.create()
            dialog.show()
        }


        return view
    }

}