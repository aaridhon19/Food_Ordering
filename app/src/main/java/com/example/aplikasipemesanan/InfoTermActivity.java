package com.example.aplikasipemesanan;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class InfoTermActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_term);
    }

//    @Override
//    public void onBackPressed(){
//        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setMessage("Apakah kamu mau keluar???");
//        builder.setCancelable(false);
//        builder.setPositiveButton(" Iya ", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                moveTaskToBack(true);
//                android.os.Process.killProcess(android.os.Process.myPid());
//                System.exit(1);
//            }
//        });
//        builder.setNegativeButton(" Tidak ", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                dialogInterface.cancel();
//            }
//        });
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//    }
}