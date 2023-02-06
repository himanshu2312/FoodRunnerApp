package com.himanshu.foodrunnerapp.datbase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RestaurantDao {

    @Insert
    fun addToFav(restaurantEntity: RestaurantEntity)

    @Delete
    fun deleteFromFav(restaurantEntity: RestaurantEntity)

    @Query(value = "SELECT * FROM favRes")
    fun getAllRes(): List<RestaurantEntity>

    @Query(value = "SELECT * FROM favRes WHERE res_id = :resId")
    fun getFavResByID(resId: String): RestaurantEntity
}