package com.example.aplikasipemesanan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.example.aplikasipemesanan.databinding.ActivityDashboardBinding;

public class TestActivity extends DrawerBaseActivity {

    ActivityDashboardBinding activityDashboardBinding;
    ViewFlipper flipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDashboardBinding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(activityDashboardBinding.getRoot());
        allocateActivityTitle("Beranda");

        int images[] = {R.drawable.indomie, R.drawable.pancong, R.drawable.gorengan, R.drawable.kopi};

        flipper = findViewById(R.id.flipper);

        // username

        //for loop
        for (int image: images){
            flipperImages(image);

        }
    }

    public void flipperImages(int image){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        flipper.addView(imageView);
        flipper.setFlipInterval(5000);
        flipper.setAutoStart(true);

        //animation
        flipper.setInAnimation(this, android.R.anim.slide_in_left);
        flipper.setOutAnimation(this, android.R.anim.slide_out_right);

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

    public void MenuGuestClicked(View view){

        Intent intent = new Intent(TestActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    public void OrderGuestClicked(View view){

        Intent intent = new Intent(TestActivity.this, OrderActivity.class);
        startActivity(intent);
    }
}