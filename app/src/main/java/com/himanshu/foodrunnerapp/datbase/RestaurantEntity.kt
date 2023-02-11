package com.himanshu.foodrunnerapp.datbase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("favRes")
data class RestaurantEntity (
      @PrimaryKey @ColumnInfo(name = "res_id") val resId: String,
      @ColumnInfo(name = "res_name") val resName: String,
      @ColumnInfo(name = "res_rating") val resRating: String,
      @ColumnInfo(name = "res_cost") val resCostforOne: String,
      @ColumnInfo(name = "res_image") val resImage:String
      )

@Entity("cartItem")
data class ItemEntity(
      @PrimaryKey @ColumnInfo(name = "id") val id: String,
      @ColumnInfo(name = "item_name") val itemName: String,
      @ColumnInfo(name = "item_cost") val itemCostForOne: String,
      @ColumnInfo(name = "res_id") val resId:String
)