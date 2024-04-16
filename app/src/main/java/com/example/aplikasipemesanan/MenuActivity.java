package com.example.aplikasipemesanan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.aplikasipemesanan.databinding.ActivityMenuBinding;

public class MenuActivity extends DrawerBaseActivity {

    ActivityMenuBinding activityMenuBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMenuBinding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(activityMenuBinding.getRoot());
        allocateActivityTitle("Menu");
    }

    @Override
    public void onBackPressed(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Apakah kamu mau keluar???");
        builder.setCancelable(false);
        builder.setPositiveButton(" Iya ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });
        builder.setNegativeButton(" Tidak ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}