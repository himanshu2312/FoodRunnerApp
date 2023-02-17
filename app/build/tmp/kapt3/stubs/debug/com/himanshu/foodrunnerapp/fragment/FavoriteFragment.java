package com.himanshu.foodrunnerapp.fragment;

import java.lang.System;

@kotlin.Suppress(names = {"DEPRECATION"})
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0014B\u0005\u00a2\u0006\u0002\u0010\u0002J&\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/himanshu/foodrunnerapp/fragment/FavoriteFragment;", "Landroidx/fragment/app/Fragment;", "()V", "favResList", "", "Lcom/himanshu/foodrunnerapp/datbase/RestaurantEntity;", "progressBar", "Landroid/widget/ProgressBar;", "progressBarLayout", "Landroid/widget/RelativeLayout;", "rvFavRestaurantList", "Landroidx/recyclerview/widget/RecyclerView;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "FavResData", "app_debug"})
public final class FavoriteFragment extends androidx.fragment.app.Fragment {
    private android.widget.ProgressBar progressBar;
    private android.widget.RelativeLayout progressBarLayout;
    private androidx.recyclerview.widget.RecyclerView rvFavRestaurantList;
    private java.util.List<com.himanshu.foodrunnerapp.datbase.RestaurantEntity> favResList;
    
    public FavoriteFragment() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u001a\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0001B\r\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J+\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0016\u0010\r\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00020\u000e\"\u0004\u0018\u00010\u0002H\u0015\u00a2\u0006\u0002\u0010\u000fR\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0010"}, d2 = {"Lcom/himanshu/foodrunnerapp/fragment/FavoriteFragment$FavResData;", "Landroid/os/AsyncTask;", "Ljava/lang/Void;", "", "Lcom/himanshu/foodrunnerapp/datbase/RestaurantEntity;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "db", "Lcom/himanshu/foodrunnerapp/datbase/ResDatabase;", "getDb", "()Lcom/himanshu/foodrunnerapp/datbase/ResDatabase;", "doInBackground", "p0", "", "([Ljava/lang/Void;)Ljava/util/List;", "app_debug"})
    public static final class FavResData extends android.os.AsyncTask<java.lang.Void, java.lang.Void, java.util.List<? extends com.himanshu.foodrunnerapp.datbase.RestaurantEntity>> {
        @org.jetbrains.annotations.NotNull()
        private final com.himanshu.foodrunnerapp.datbase.ResDatabase db = null;
        
        public FavResData(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.himanshu.foodrunnerapp.datbase.ResDatabase getDb() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        @java.lang.Deprecated()
        protected java.util.List<com.himanshu.foodrunnerapp.datbase.RestaurantEntity> doInBackground(@org.jetbrains.annotations.NotNull()
        java.lang.Void... p0) {
            return null;
        }
    }
}