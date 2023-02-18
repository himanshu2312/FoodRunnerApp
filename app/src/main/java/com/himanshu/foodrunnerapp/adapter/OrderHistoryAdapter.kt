package com.himanshu.foodrunnerapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.himanshu.foodrunnerapp.R
import com.himanshu.foodrunnerapp.datbase.OrderHistory

class OrderHistoryAdapter(private val context: Context,private val orderList: List<OrderHistory>): RecyclerView.Adapter<OrderHistoryAdapter.HistoryViewHolder>() {
    class HistoryViewHolder(view: View): RecyclerView.ViewHolder(view){
        val txtRestaurantName: TextView=view.findViewById(R.id.txtRestaurantName)
        val txtDate: TextView=view.findViewById(R.id.txtDate)
        val rvItems: RecyclerView=view.findViewById(R.id.rvItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_history_item,parent,false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
          val order=orderList[position]
          holder.txtRestaurantName.text=order.resName
          holder.txtDate.text=order.orderPlacedAt.subSequence(0,9)
          holder.rvItems.adapter=CartIItemAdapter(context,order.itemList)
          holder.rvItems.layoutManager=LinearLayoutManager(context)
    }

    override fun getItemCount(): Int {
        return orderList.size
    }
}