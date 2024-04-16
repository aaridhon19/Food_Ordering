package com.example.aplikasipemesanan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {
    EditText editTextUsername, editTextPassword;
    TextView textViewForgetPassword, textViewRegister;
    ProgressBar progressBar;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        // getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        editTextUsername = findViewById(R.id.editTextSignInUsername);
        editTextPassword = findViewById(R.id.editTextSignInPassword);

        textViewForgetPassword = findViewById(R.id.txtSignInForgotPassword);
        textViewRegister = findViewById(R.id.txtSignInRegister);

        progressBar = (ProgressBar) findViewById(R.id.progressBarSignIn);

        mAuth = FirebaseAuth.getInstance();
    }

    public void textSignInForgotPasswordClicked(View v){
        Intent intent = new Intent(this,ForgotPasswordActivity.class);
        startActivity(intent);
    }

    public void textSignInRegisterClicked(View v){
        Intent intent = new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }

    public void buttonSignInSignInClicked(View v){

        String userName = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (!Patterns.EMAIL_ADDRESS.matcher(userName).matches()){
            editTextUsername.setError("Harap Masukkan Email yang Benar");
            editTextUsername.requestFocus();
            return;
        }
        else if (editTextPassword.length() < 6){
            editTextPassword.setError("Harap Masukkan Password Minimal 6 Karakter");
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(userName, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(SignInActivity.this, "Pengguna telah Masuk", Toast.LENGTH_LONG).show();

                    startActivity(new Intent(SignInActivity.this, MainActivity.class));
                }
                else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(SignInActivity.this, "Pengguna Gagal untuk Masuk", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}