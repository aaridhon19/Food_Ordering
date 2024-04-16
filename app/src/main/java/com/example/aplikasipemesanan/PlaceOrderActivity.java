package com.example.aplikasipemesanan;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasipemesanan.model.Menu;
import com.example.aplikasipemesanan.adapters.PlaceOrderAdapter;
import com.example.aplikasipemesanan.model.RestaurantModel;

public class PlaceOrderActivity extends AppCompatActivity {

    private EditText inputName, inputCardNumber, inputCardExpiry ;
    private RecyclerView cartItemsRecyclerView;
    private TextView tvSubtotalAmount, tvTotalAmount, buttonPlaceOrder;
//    private SwitchCompat switchDelivery;
//    private boolean isDeliveryOn;
    private PlaceOrderAdapter placeOrderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);

        RestaurantModel restaurantModel = getIntent().getParcelableExtra("RestaurantModel");
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setTitle(restaurantModel.getName());
//        actionBar.setSubtitle(restaurantModel.getAddress());
//        actionBar.setDisplayHomeAsUpEnabled(true);

        inputName = findViewById(R.id.inputName);
//        inputAddress = findViewById(R.id.inputAddress);
//        inputCity = findViewById(R.id.inputCity);
//        inputState = findViewById(R.id.inputState);
//        inputZip = findViewById(R.id.inputZip);
        inputCardNumber = findViewById(R.id.inputCardNumber);
        inputCardExpiry = findViewById(R.id.inputCardExpiry);
//        inputCardPin = findViewById(R.id.inputCardPin);
//        tvSubtotalAmount = findViewById(R.id.tvSubtotalAmount);
//        tvDeliveryChargeAmount = findViewById(R.id.tvDeliveryChargeAmount);
//        tvDeliveryCharge = findViewById(R.id.tvDeliveryCharge);
        tvTotalAmount = findViewById(R.id.tvTotalAmount);
        buttonPlaceOrder = findViewById(R.id.buttonPlaceOrder);
//        switchDelivery = findViewById(R.id.switchDelivery);

        cartItemsRecyclerView = findViewById(R.id.cartItemsRecyclerView);

        buttonPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPlaceOrderButtonClick(restaurantModel);
            }
        });

//        switchDelivery.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(isChecked) {
//                    inputAddress.setVisibility(View.VISIBLE);
//                    inputCity.setVisibility(View.VISIBLE);
//                    inputState.setVisibility(View.VISIBLE);
//                    inputZip.setVisibility(View.VISIBLE);
//                    tvDeliveryChargeAmount.setVisibility(View.VISIBLE);
//                    tvDeliveryCharge.setVisibility(View.VISIBLE);
//                    isDeliveryOn = true;
//                    calculateTotalAmount(restaurantModel);
//                } else {
//                    inputAddress.setVisibility(View.GONE);
//                    inputCity.setVisibility(View.GONE);
//                    inputState.setVisibility(View.GONE);
//                    inputZip.setVisibility(View.GONE);
//                    tvDeliveryChargeAmount.setVisibility(View.GONE);
//                    tvDeliveryCharge.setVisibility(View.GONE);
//                    isDeliveryOn = false;
//                    calculateTotalAmount(restaurantModel);
//                }
//            }
//        });
        initRecyclerView(restaurantModel);
        calculateTotalAmount(restaurantModel);
    }

    private void calculateTotalAmount(RestaurantModel restaurantModel) {
        float subTotalAmount = 0f;

        for(Menu m : restaurantModel.getMenus()) {
            subTotalAmount += m.getPrice() * m.getTotalInCart();
        }

//        tvSubtotalAmount.setText("Rp. "+String.format("%.2f", subTotalAmount)+"0");

//        if(isDeliveryOn) {
//            tvDeliveryChargeAmount.setText("Rp. "+String.format("%.2f", restaurantModel.getDelivery_charge()));
//            subTotalAmount += restaurantModel.getDelivery_charge();
//        }
        tvTotalAmount.setText("Rp. "+String.format("%.2f", subTotalAmount)+"0");
    }

    private void onPlaceOrderButtonClick(RestaurantModel restaurantModel) {
        if(TextUtils.isEmpty(inputName.getText().toString())) {
            inputName.setError("Harap Masukkan Nama ");
            inputName.requestFocus();
            return;
//        } else if(isDeliveryOn && TextUtils.isEmpty(inputAddress.getText().toString())) {
//            inputAddress.setError("Please enter address ");
//            return;
//        }else if(isDeliveryOn && TextUtils.isEmpty(inputCity.getText().toString())) {
//            inputCity.setError("Please enter city ");
//            return;
//        }else if(isDeliveryOn && TextUtils.isEmpty(inputState.getText().toString())) {
//            inputState.setError("Please enter zip ");
//            return;
        }else if( TextUtils.isEmpty(inputCardNumber.getText().toString())) {
            inputCardNumber.setError("Harap Masukkan Nomor Meja");
            inputCardNumber.requestFocus();
            return;
        }else if( TextUtils.isEmpty(inputCardExpiry.getText().toString())) {
            inputCardExpiry.setError("Harap Masukkan Catatan Pesanan");
            inputCardExpiry.requestFocus();
            return;
//        }else if( TextUtils.isEmpty(inputCardPin.getText().toString())) {
//            inputCardPin.setError("Please enter card pin/cvv ");
//            return;
        }
        //start success activity..
//            startActivity(new Intent(PlaceOrderActivity.this, OrderSuccessActivity.class));
//            Intent intent = new Intent(PlaceOrderActivity.this, OrderSuccessActivity.class);
//            startActivity(intent);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Apakah kamu ingin melanjutkan pemesanan?");
        builder.setCancelable(false);
        builder.setPositiveButton(" Iya ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                moveTaskToBack(true);
                startActivity(new Intent(PlaceOrderActivity.this, OrderSuccessActivity.class));
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

//        Intent i = new Intent(PlaceOrderActivity.this, OrderSuccessActivity.class);
//        i.putExtra("RestaurantModel", restaurantModel);
//        startActivityForResult(i, 1000);
    }

    private void initRecyclerView(RestaurantModel restaurantModel) {
        cartItemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        placeOrderAdapter = new PlaceOrderAdapter(restaurantModel.getMenus());
        cartItemsRecyclerView.setAdapter(placeOrderAdapter);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//
//        if(requestCode == 1000) {
//            setResult(Activity.RESULT_OK);
//            finish();
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }

//    @Override
//    protected void onActivityResult(@Nullable Intent data) {
//
//        if(requestCode == 1000) {
//            setResult(Activity.RESULT_OK);
//            finish();
//        }
//        super.onActivityResult(data);
//    }
//
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

    if(requestCode == 1000) {
        setResult(Activity.RESULT_OK);
        finish();
    }
    super.onActivityResult(requestCode, resultCode, data);
}

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home :
                finish();
            default:
                //do nothing
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Apakah kamu ingin membatalkan pesanan?");
        builder.setCancelable(false);
        builder.setPositiveButton(" Iya ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                moveTaskToBack(true);
                startActivity(new Intent(PlaceOrderActivity.this, RestaurantMenuActivity.class));
                Toast.makeText(PlaceOrderActivity.this, "Pengguna telah membatalkan Pesanan", Toast.LENGTH_SHORT).show();
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