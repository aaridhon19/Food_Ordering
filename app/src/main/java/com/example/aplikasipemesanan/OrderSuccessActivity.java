package com.example.aplikasipemesanan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OrderSuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_success);


//        RestaurantModel restaurantModel = getIntent().getParcelableExtra("RestaurantModel");
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setTitle(restaurantModel.getName());
//        actionBar.setSubtitle(restaurantModel.getAddress());
//        actionBar.setDisplayHomeAsUpEnabled(false);

        TextView buttonDone = findViewById(R.id.buttonDone);
        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(OrderSuccessActivity.this);
                builder.setMessage("Ingin keluar dari akun???");
                builder.setCancelable(false);
                builder.setPositiveButton(" Iya ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        moveTaskToBack(true);
                        startActivity(new Intent(OrderSuccessActivity.this, WelcomeActivity.class));
                        Toast.makeText(OrderSuccessActivity.this, "Pengguna telah Keluar", Toast.LENGTH_LONG).show();
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
        });

        TextView buttonOrderAgain = findViewById(R.id.buttonOrderAgain);
        buttonOrderAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderSuccessActivity.this, MainActivity.class));
            }
        });
    }
}

//    public void buttonOrderAgainClicked (View view){
//        Intent intent = new Intent(OrderSuccessActivity.this, PlaceOrderActivity.class);
//        startActivity(intent);
//    }

//    Intent i = new Intent(OrderSuccessActivity.this, PlaceOrderActivity.class);
////        i.putExtra("RestaurantModel", restaurantModel);
//    startActivityForResult(i, 1000);
//}
