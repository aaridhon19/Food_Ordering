package com.example.aplikasipemesanan;

import android.os.Bundle;

import com.example.aplikasipemesanan.databinding.ActivityFavoriteBinding;

public class FavoriteActivity extends DrawerBaseActivity {

    ActivityFavoriteBinding activityFavoriteBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityFavoriteBinding = ActivityFavoriteBinding.inflate(getLayoutInflater());
        setContentView(activityFavoriteBinding.getRoot());
        allocateActivityTitle("Favorite");

//        setContentView(R.layout.activity_favorite);
    }
}