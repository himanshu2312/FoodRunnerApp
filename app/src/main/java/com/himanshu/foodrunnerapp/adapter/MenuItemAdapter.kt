@file:Suppress("DEPRECATION")

package com.himanshu.foodrunnerapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.himanshu.foodrunnerapp.R
import com.himanshu.foodrunnerapp.datbase.ItemDatabase
import com.himanshu.foodrunnerapp.datbase.ItemEntity

@Suppress("DEPRECATION")
class MenuItemAdapter(
    private val context: Context,
    private val itemList: List<ItemEntity>,
    private val btnCart: AppCompatButton
) : RecyclerView.Adapter<MenuItemAdapter.ItemViewHolder>() {
    private var count: Int =0
    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtId: TextView = view.findViewById(R.id.txtItemId)
        val txtItemName: TextView = view.findViewById(R.id.txtItemName)
        val txtItemPrice: TextView = view.findViewById(R.id.txtItemPrice)
        val btnAddToCart: AppCompatButton = view.findViewById(R.id.btnAddToCart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.single_menu_item, parent, false)
        return ItemViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = itemList[position]
        holder.txtId.text = item.id
        holder.txtItemName.text = item.itemName
        val text = "Rs.${item.itemCostForOne}"
        holder.txtItemPrice.text = text
        if (CartItemData(context, item, 1).execute().get()) {
            holder.btnAddToCart.text = "Remove"
            holder.btnAddToCart.setBackgroundColor(context.resources.getColor(R.color.ratingGold))
        }
        
        holder.btnAddToCart.setOnClickListener {
            if (!CartItemData(context, item, 1).execute().get()) {
                if (CartItemData(context, item, 2).execute().get()) {
                    holder.btnAddToCart.text = "Remove"
                    holder.btnAddToCart.setBackgroundColor(context.resources.getColor(R.color.ratingGold))
                    count++
                } else {
                    Toast.makeText(
                        context,
                        "Some error occurred!!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            else {
                if (CartItemData(context, item, 3).execute().get()) {
                    holder.btnAddToCart.text = "Add"
                    holder.btnAddToCart.setBackgroundColor(context.resources.getColor(R.color.color_primary))
                    count--
                } else {
                    Toast.makeText(
                        context,
                        "Some error occurred!!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            when(count){
                0 -> btnCart.visibility=View.GONE
                else -> btnCart.visibility=View.VISIBLE
            }

        }

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    @Suppress("DEPRECATION")
    class CartItemData(
        context: Context,
        private val itemEntity: ItemEntity,
        private val mode: Int
    ) : AsyncTask<Void, Void, Boolean>() {
        private val db = Room.databaseBuilder(context, ItemDatabase::class.java, "item_db").build()

        @Deprecated("Deprecated in Java")
        override fun doInBackground(vararg p0: Void?): Boolean {
            /*mode: 1 check for res in itemList
            mode: 2 insert res in itemList
            else : delete res from itemList */
            when (mode) {
                1 -> {
                    val item: ItemEntity? = db.itemDao().getCartItemByID(itemEntity.id)
                    db.close()
                    return item != null
                }
                2 -> {
                    db.itemDao().addToCart(itemEntity)
                    db.close()
                    return true
                }
                else -> {
                    db.itemDao().deleteFromCart(itemEntity)
                    db.close()
                    return true
                }
            }
        }

    }
}