@file:Suppress("DEPRECATION")

package com.himanshu.foodrunnerapp.activity

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.MenuItem
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.himanshu.foodrunnerapp.R
import com.himanshu.foodrunnerapp.adapter.CartIItemAdapter
import com.himanshu.foodrunnerapp.datbase.ItemDatabase
import com.himanshu.foodrunnerapp.datbase.ItemEntity
import com.himanshu.foodrunnerapp.util.ConnectionManager
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

@Suppress("DEPRECATION")
class CartActivity : AppCompatActivity() {
    private lateinit var progressBarLayout: RelativeLayout
    private lateinit var txtResName: TextView
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var rvCartItem: RecyclerView
    private lateinit var btnPlaceOrder: AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        val sharedPreferences=getSharedPreferences(R.string.app_name.toString(), MODE_PRIVATE)
        toolbar=findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title="My Cart"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        progressBarLayout=findViewById(R.id.progressBarLayout)
        btnPlaceOrder=findViewById(R.id.btnPlaceOrder)
        btnPlaceOrder.visibility=View.GONE
        progressBarLayout.visibility=View.VISIBLE
        txtResName=findViewById(R.id.txtResName)
        txtResName.text=sharedPreferences.getString("res_name","Restaurant")
        rvCartItem=findViewById(R.id.rvCartItem)
        val itemList =ItemData(this@CartActivity).execute().get()
        var totalPrice=0
        for (element in itemList){
            totalPrice+=element.itemCostForOne.toInt()
        }
        rvCartItem.layoutManager=LinearLayoutManager(this@CartActivity)
        rvCartItem.adapter=CartIItemAdapter(this@CartActivity,itemList)
        val btnText="Place Order(Total: Rs.$totalPrice)"
        btnPlaceOrder.text= btnText
        btnPlaceOrder.visibility=View.VISIBLE
        progressBarLayout.visibility=View.GONE

        btnPlaceOrder.setOnClickListener {
            if (ConnectionManager().checkConnectivity(this@CartActivity)) {
                val queue = Volley.newRequestQueue(this@CartActivity)
                val url = "http://13.235.250.119/v2/place_order/fetch_result/"
                val params= JSONObject()
                val sharedPreferences=getSharedPreferences(R.string.app_name.toString(),
                    MODE_PRIVATE)
                params.put("user_id",sharedPreferences.getString("user_id","00"))
                params.put("restaurant_id",sharedPreferences.getString("res_id","00"))
                params.put("total_cost",totalPrice.toString())
                val foodArray=JSONArray()
                for (i in itemList.indices){
                    val item=JSONObject()
                    item.put("food_item_id",itemList[i].id)
                    foodArray.put(i,item)
                }
                params.put("food",foodArray)
                println(params)
                val jsonObjectRequest = object : JsonObjectRequest(Method.POST, url, params,
                    Response.Listener {
                        println(it)
                        try {
                            if (it.getJSONObject("data").getBoolean("success")){
                                val intent=Intent(this@CartActivity,OrderSuccessActivity::class.java)
                                startActivity(intent)
                                finish()
                            }else{
                                Toast.makeText(
                                    this@CartActivity,
                                    it.getJSONObject("data").getString("errorMessage"),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }catch (e: JSONException){
                            Toast.makeText(
                                this@CartActivity,
                                "$e",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }, Response.ErrorListener {
                        Toast.makeText(
                            this@CartActivity,
                            "volley error occurred!!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }) {
                    override fun getHeaders(): MutableMap<String, String> {
                        val header = HashMap<String, String>()
                        header["Content-Type"] = "application/json"
                        header["token"] = "56e490debc1169"
                        return header
                    }
                }
                queue.add(jsonObjectRequest)
            } else {
                val dialog = AlertDialog.Builder(this@CartActivity)
                dialog.setMessage("Internet Connection Error:")
                dialog.setNegativeButton("Exit") { text, listener ->
                    ActivityCompat.finishAffinity(this@CartActivity)
                }
                dialog.setPositiveButton("Open Settings") { text, listener ->
                    val intent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
                    startActivity(intent)
                    finish()
                }
                dialog.create()
                dialog.show()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==android.R.id.home){
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    class ItemData(context: Context): AsyncTask<Void,Void,List<ItemEntity>>(){
        private val db= Room.databaseBuilder(context,ItemDatabase::class.java,"item_db").build()
        @Deprecated("Deprecated in Java")
        override fun doInBackground(vararg p0: Void?): List<ItemEntity> {
            return db.itemDao().getAllItem()
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this@CartActivity,MenuActivity::class.java)
        startActivity(intent)
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}