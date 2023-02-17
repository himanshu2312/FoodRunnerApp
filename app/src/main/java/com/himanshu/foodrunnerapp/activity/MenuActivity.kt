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
import com.android.volley.Response
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.himanshu.foodrunnerapp.R
import com.himanshu.foodrunnerapp.adapter.MenuItemAdapter
import com.himanshu.foodrunnerapp.datbase.ItemDatabase
import com.himanshu.foodrunnerapp.datbase.ItemEntity
import com.himanshu.foodrunnerapp.util.ConnectionManager
import org.json.JSONException

@Suppress("DEPRECATION")
class MenuActivity : AppCompatActivity() {
    private lateinit var progressBarLayout: RelativeLayout
    private lateinit var btnCart: AppCompatButton
    private lateinit var rvMenuItem: RecyclerView
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private var resId:String? = null
    private val itemList= arrayListOf<ItemEntity>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        toolbar=findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val  sharedPreferences=getSharedPreferences(R.string.app_name.toString(), MODE_PRIVATE)
        val title= sharedPreferences.getString("res_name","Menu")
        supportActionBar?.title = title
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        progressBarLayout = findViewById(R.id.progressBarLayout)
        btnCart = findViewById(R.id.btnCart)
        btnCart.visibility = View.GONE
        progressBarLayout.visibility = View.VISIBLE
        rvMenuItem = findViewById(R.id.rvMenuItem)
        resId=sharedPreferences.getString("res_id",null)
        if (resId==null){
            Toast.makeText(
                this@MenuActivity,
                "resId Null!!",
                Toast.LENGTH_SHORT
            ).show()
        }
        else if (ConnectionManager().checkConnectivity(this@MenuActivity)) {
            val queue = Volley.newRequestQueue(this@MenuActivity)
            val url = "http://13.235.250.119/v2/restaurants/fetch_result/${resId}"
            val jsonObjectRequest = object : JsonObjectRequest(Method.GET, url, null,
                Response.Listener {
                    try {
                        if (it.getJSONObject("data").getBoolean("success")){
                             val data=it.getJSONObject("data").getJSONArray("data")
                             for (i in 0 until data.length()) {
                                 val item=data.getJSONObject(i)
                                 val itemEntity=ItemEntity(
                                     item.getString("id"),
                                     item.getString("name"),
                                     item.getString("cost_for_one"),
                                     item.getString("restaurant_id")
                                 )
                                 itemList.add(itemEntity)
                             }
                             progressBarLayout.visibility=View.GONE
                             rvMenuItem.adapter=MenuItemAdapter(this@MenuActivity,itemList,btnCart)
                             rvMenuItem.layoutManager=LinearLayoutManager(this@MenuActivity)
                        }else{
                            Toast.makeText(
                                this@MenuActivity,
                                it.getJSONObject("data").getString("errorMessage"),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }catch (e: JSONException){
                        Toast.makeText(
                            this@MenuActivity,
                            "$e",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }, Response.ErrorListener {
                    Toast.makeText(
                        this@MenuActivity,
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
            val dialog = AlertDialog.Builder(this@MenuActivity)
            dialog.setMessage("Internet Connection Error:")
            dialog.setNegativeButton("Exit") { text, listener ->
                ActivityCompat.finishAffinity(this@MenuActivity)
            }
            dialog.setPositiveButton("Open Settings") { text, listener ->
                val intent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
                startActivity(intent)
                finish()
            }
            dialog.create()
            dialog.show()
        }
        btnCart.setOnClickListener {
            val intent=Intent(this@MenuActivity,CartActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==android.R.id.home){
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }


    class ItemData(context: Context):AsyncTask<Void,Void,Boolean>(){
        private val db=Room.databaseBuilder(context,ItemDatabase::class.java,"item_db").build()
        @Deprecated("Deprecated in Java")
        override fun doInBackground(vararg p0: Void?): Boolean {
            db.itemDao().deleteAllItem()
            db.close()
            return true
        }

    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (!ItemData(this@MenuActivity).execute().get()){
            Toast.makeText(
                this@MenuActivity,
                "something went wrong",
                Toast.LENGTH_SHORT
            ).show()
        }
        val intent = Intent(this@MenuActivity, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onStop() {
        if (!ItemData(this@MenuActivity).execute().get()){
            Toast.makeText(
                this@MenuActivity,
                "something went wrong",
                Toast.LENGTH_SHORT
            ).show()
        }
        super.onStop()
    }

    override fun onPause() {
        super.onPause()
        finish()
    }


}