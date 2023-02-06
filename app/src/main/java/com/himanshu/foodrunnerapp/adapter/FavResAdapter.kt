package com.himanshu.foodrunnerapp.adapter

import android.content.Context
import android.content.res.Resources
import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.himanshu.foodrunnerapp.R
import com.himanshu.foodrunnerapp.datbase.ResDatabase
import com.himanshu.foodrunnerapp.datbase.RestaurantEntity
import com.squareup.picasso.Picasso

@Suppress("DEPRECATION")
class FavResAdapter(val context: Context, private val resList: List<RestaurantEntity>) :
    RecyclerView.Adapter<FavResAdapter.FavRestaurantViewHolder>() {

    class FavRestaurantViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtRestaurantName: TextView = view.findViewById(R.id.txtRestaurantName)
        val txtRestaurantPrice: TextView = view.findViewById(R.id.txtRestaurantPrice)
        val txtRestaurantRating: TextView = view.findViewById(R.id.txtRestaurantRating)
        val imgRestaurantImage: ImageView = view.findViewById(R.id.imgRestaurantImage)
        val imgFavorite: ImageView = view.findViewById(R.id.imgFavorite)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavRestaurantViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.single_rastaurant, parent, false)
        return FavRestaurantViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavRestaurantViewHolder, position: Int) {
        val res = resList[position]
        holder.txtRestaurantName.text = res.resName
        holder.txtRestaurantPrice.text = res.resCostforOne
        holder.txtRestaurantRating.text = res.resRating
        Picasso.get().load(res.resImage).error(R.drawable.food_runner)
            .into(holder.imgRestaurantImage)
        holder.imgFavorite.setImageResource(R.drawable.ic_remove_from_fav)
        holder.imgFavorite.setOnClickListener {

                if (FavResData(context, res).execute().get()) {
                    holder.imgFavorite.setImageResource(R.drawable.ic_add_to_fav)
                    Toast.makeText(
                        context,
                        "Restaurant removed from favorites!!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        context,
                        "Some error occurred!!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }
    }

    override fun getItemCount(): Int {
        return resList.size
    }

    @Suppress("DEPRECATION")
    class FavResData(private val context: Context, private val restaurantEntity: RestaurantEntity) :
        AsyncTask<Void, Void, Boolean>() {
        private val db = Room.databaseBuilder(context, ResDatabase::class.java, "res_db").build()
        override fun doInBackground(vararg p0: Void?): Boolean {
            db.resDao().deleteFromFav(restaurantEntity)
            db.close()
            return true
        }

    }
}