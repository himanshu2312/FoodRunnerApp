package com.himanshu.foodrunnerapp.datbase;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\bH\'J\u0010\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\'\u00a8\u0006\f"}, d2 = {"Lcom/himanshu/foodrunnerapp/datbase/RestaurantDao;", "", "addToFav", "", "restaurantEntity", "Lcom/himanshu/foodrunnerapp/datbase/RestaurantEntity;", "deleteFromFav", "getAllRes", "", "getFavResByID", "resId", "", "app_debug"})
public abstract interface RestaurantDao {
    
    @androidx.room.Insert()
    public abstract void addToFav(@org.jetbrains.annotations.NotNull()
    com.himanshu.foodrunnerapp.datbase.RestaurantEntity restaurantEntity);
    
    @androidx.room.Delete()
    public abstract void deleteFromFav(@org.jetbrains.annotations.NotNull()
    com.himanshu.foodrunnerapp.datbase.RestaurantEntity restaurantEntity);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM favRes")
    public abstract java.util.List<com.himanshu.foodrunnerapp.datbase.RestaurantEntity> getAllRes();
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM favRes WHERE res_id = :resId")
    public abstract com.himanshu.foodrunnerapp.datbase.RestaurantEntity getFavResByID(@org.jetbrains.annotations.NotNull()
    java.lang.String resId);
}