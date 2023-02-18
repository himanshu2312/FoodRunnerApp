package com.himanshu.foodrunnerapp.fragment

import android.app.Activity
import android.app.AlertDialog
import android.content.ClipData.Item
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.himanshu.foodrunnerapp.R
import com.himanshu.foodrunnerapp.adapter.OrderHistoryAdapter
import com.himanshu.foodrunnerapp.adapter.RestaurantListAdapter
import com.himanshu.foodrunnerapp.datbase.ItemEntity
import com.himanshu.foodrunnerapp.datbase.OrderHistory
import com.himanshu.foodrunnerapp.datbase.RestaurantEntity
import com.himanshu.foodrunnerapp.util.ConnectionManager
import org.json.JSONException


class OrderHistoryFragment : Fragment() {
    private lateinit var progressBarLayout: RelativeLayout
    private lateinit var rvOrderHistory: RecyclerView
    private var orderList= arrayListOf<OrderHistory>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_order_history, container, false)
        progressBarLayout = view.findViewById(R.id.progressBarLayout)
        progressBarLayout.visibility = View.VISIBLE
        rvOrderHistory = view.findViewById(R.id.rvOrderHistory)
        if (ConnectionManager().checkConnectivity(activity as Context)) {
            val queue = Volley.newRequestQueue(activity as Context)
            val sharedPreferences=(activity as Context).getSharedPreferences(R.string.app_name.toString(),Context.MODE_PRIVATE)
            val url = "http://13.235.250.119/v2/orders/fetch_result/${sharedPreferences.getString("user_id","-1")}"
            val jsonObjectRequest = object : JsonObjectRequest(
                Method.GET, url, null,
                Response.Listener {
                    if (it.getJSONObject("data").getBoolean("success")) {
                        try {
                            val data = it.getJSONObject("data").getJSONArray("data")
                            for (i in 0 until data.length()) {
                                val historyObj = data.getJSONObject(i)
                                //item list gathering
                                val itemList= arrayListOf<ItemEntity>()
                                val foodArray= historyObj.getJSONArray("food_items")
                                for(j in 0 until foodArray.length()){
                                    val foodItem=foodArray.getJSONObject(j)
                                    val itemEntity=ItemEntity(
                                        foodItem.getString("food_item_id"),
                                        foodItem.getString("name"),
                                        foodItem.getString("cost"),
                                        "-1"
                                    )
                                    itemList.add(itemEntity)
                                }
                                //out
                                val orderHistory = OrderHistory(
                                    historyObj.getString("order_id"),
                                    historyObj.getString("restaurant_name"),
                                    historyObj.getString("total_cost"),
                                    historyObj.getString("order_placed_at"),
                                    itemList
                                )
                                orderList.add(orderHistory)
                            }
                                if (activity != null) {
                                    progressBarLayout.visibility = View.GONE
                                    rvOrderHistory.adapter =
                                        OrderHistoryAdapter(activity as Context,orderList)
                                    rvOrderHistory.layoutManager =
                                        LinearLayoutManager(activity as Context)
                                } else {
                                    Toast.makeText(
                                        activity as Context,
                                        "something went wrong",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                        } catch (e: JSONException) {
                            Toast.makeText(
                                activity as Context,
                                "Json error occurred",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            activity as Context,
                            it.getJSONObject("data").getString("errorMessage"),
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
            dialog.setMessage("Internet Connection Error:")
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