package com.himanshu.foodrunnerapp.datbase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RestaurantEntity::class], version = 1)
abstract class ResDatabase: RoomDatabase() {
    abstract fun resDao(): RestaurantDao
}

@Database(entities = [ItemEntity::class], version = 1)
abstract class ItemDatabase: RoomDatabase() {
    abstract fun itemDao(): ItemDao
}