package com.himanshu.foodrunnerapp.datbase;

import java.lang.System;

@androidx.room.Entity(tableName = "favRes")
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J;\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0018\u001a\u00020\u0019H\u00d6\u0001J\t\u0010\u001a\u001a\u00020\u0003H\u00d6\u0001R\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0016\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n\u00a8\u0006\u001b"}, d2 = {"Lcom/himanshu/foodrunnerapp/datbase/RestaurantEntity;", "", "resId", "", "resName", "resRating", "resCostforOne", "resImage", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getResCostforOne", "()Ljava/lang/String;", "getResId", "getResImage", "getResName", "getResRating", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
public final class RestaurantEntity {
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "res_id")
    @androidx.room.PrimaryKey()
    private final java.lang.String resId = null;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "res_name")
    private final java.lang.String resName = null;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "res_rating")
    private final java.lang.String resRating = null;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "res_cost")
    private final java.lang.String resCostforOne = null;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "res_image")
    private final java.lang.String resImage = null;
    
    @org.jetbrains.annotations.NotNull()
    public final com.himanshu.foodrunnerapp.datbase.RestaurantEntity copy(@org.jetbrains.annotations.NotNull()
    java.lang.String resId, @org.jetbrains.annotations.NotNull()
    java.lang.String resName, @org.jetbrains.annotations.NotNull()
    java.lang.String resRating, @org.jetbrains.annotations.NotNull()
    java.lang.String resCostforOne, @org.jetbrains.annotations.NotNull()
    java.lang.String resImage) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    public RestaurantEntity(@org.jetbrains.annotations.NotNull()
    java.lang.String resId, @org.jetbrains.annotations.NotNull()
    java.lang.String resName, @org.jetbrains.annotations.NotNull()
    java.lang.String resRating, @org.jetbrains.annotations.NotNull()
    java.lang.String resCostforOne, @org.jetbrains.annotations.NotNull()
    java.lang.String resImage) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getResId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getResName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getResRating() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getResCostforOne() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getResImage() {
        return null;
    }
}