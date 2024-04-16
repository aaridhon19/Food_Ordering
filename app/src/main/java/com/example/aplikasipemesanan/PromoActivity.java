package com.example.aplikasipemesanan;

import android.os.Bundle;

import com.example.aplikasipemesanan.databinding.ActivityPromoBinding;

public class PromoActivity extends DrawerBaseActivity {

    ActivityPromoBinding activityPromoBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPromoBinding = ActivityPromoBinding.inflate(getLayoutInflater());
        setContentView(activityPromoBinding.getRoot());
        allocateActivityTitle("Promo");
//        setContentView(R.layout.activity_promo);
    }
}