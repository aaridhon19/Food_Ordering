package com.example.aplikasipemesanan;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("Aplikasi_Pemesanan", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Aplikasi_Pemesanan", "Failed to read value.", error.toException());
            }
        });
//        ValueEventListener valueEventListener = myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
//                Log.d("Aplikasi_Pemesanan", "Value is: " + value);
//            }
//
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w("Aplikasi_Pemenasan", "Failed to read value.", error.toException());
//            }
//        });
    }

    public void onButtonSignUpClicked(View view) {

        Intent intent = new Intent(WelcomeActivity.this,SignUpActivity.class);
        startActivity(intent);

    }

    public void onButtonLogInClicked(View view) {

        Intent intent = new Intent(WelcomeActivity.this,SignInActivity.class);
        startActivity(intent);
    }

//    public void TextLogInAsGuestClicked(View view){
//
//        Intent intent = new Intent(WelcomeActivity.this, DashboardActivity.class);
//        startActivity(intent);
//    }

    private static final int TIME_INTERVAL = 2000;
    private long backPressed;

    @Override
    public void onBackPressed(){
        if(backPressed + TIME_INTERVAL > System.currentTimeMillis()){
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                moveTaskToBack(true);
                super.onBackPressed();
                return;
        }
        else {
            Toast.makeText(getBaseContext(), "Tekan sekali lagi untuk keluar", Toast.LENGTH_SHORT).show();
        }
        backPressed = System.currentTimeMillis();
    }

}