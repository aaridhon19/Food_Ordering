package com.example.aplikasipemesanan;

import android.os.Bundle;

import com.example.aplikasipemesanan.databinding.ActivityHistoryBinding;

public class HistoryActivity extends DrawerBaseActivity {

    ActivityHistoryBinding activityHistoryBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityHistoryBinding = ActivityHistoryBinding.inflate(getLayoutInflater());
        setContentView(activityHistoryBinding.getRoot());
        allocateActivityTitle("History");
//        setContentView(R.layout.activity_history);
    }
}