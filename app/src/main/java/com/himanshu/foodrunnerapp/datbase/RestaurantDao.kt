package com.himanshu.foodrunnerapp.datbase

import androidx.room.*

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

@Dao
interface ItemDao {

    @Insert
    fun addToCart(itemEntity: ItemEntity)

    @Delete
    fun deleteFromCart(itemEntity: ItemEntity)

    @Query(value = "SELECT * FROM cartItem")
    fun getAllItem(): List<ItemEntity>

    @Query(value = "SELECT * FROM cartItem WHERE id = :itemId")
    fun getCartItemByID(itemId: String): ItemEntity
}