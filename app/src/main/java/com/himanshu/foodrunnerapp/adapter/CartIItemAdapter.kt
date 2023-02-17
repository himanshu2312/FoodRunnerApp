package com.himanshu.foodrunnerapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.himanshu.foodrunnerapp.R
import com.himanshu.foodrunnerapp.datbase.ItemEntity

class CartIItemAdapter(context: Context,private val itemList: List<ItemEntity>): RecyclerView.Adapter<CartIItemAdapter.ItemViewHolder>() {
    class ItemViewHolder(view: View): RecyclerView.ViewHolder(view){
        val txtResName: TextView=view.findViewById(R.id.txtRestaurantName)
        val txtResPrice: TextView=view.findViewById(R.id.txtRestaurantPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.single_cart_item,parent,false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item=itemList[position]
        holder.txtResName.text=item.itemName
        val price="Rs.${item.itemCostForOne}"
        holder.txtResPrice.text=price
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

}