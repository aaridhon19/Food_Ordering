package com.example.aplikasipemesanan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    EditText editTextUsername;
    EditText editTextPassword;
    EditText editTextEmail;
    EditText editTextNoHP;
    ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

//        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        editTextUsername = (EditText)findViewById(R.id.editTextUsername);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextNoHP = (EditText)findViewById(R.id.editTextNoHP);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();
    }

    public void signupButtonClicked(View v) {

        String txtUsername = editTextUsername.getText().toString().trim();
        String txtPassword = editTextPassword.getText().toString().trim();
        String txtEmail = editTextEmail.getText().toString().trim();
        String txtNoHP = editTextNoHP.getText().toString().trim();

        if (txtUsername.isEmpty()){
            editTextUsername.setError("Masukkan Username!!!");
            editTextUsername.requestFocus();
            return;
        }

        else if (txtPassword.isEmpty() || txtPassword.length() < 6){
            editTextPassword.setError("Masukkan Kata Sandi Minimal 6 Karakter!!!");
            editTextPassword.requestFocus();
            return;
        }

        else if (txtEmail.isEmpty()){
            editTextEmail.setError("Masukkan Email!!!");
            editTextEmail.requestFocus();
            return;
        }

        else if (txtNoHP.isEmpty()){
            editTextNoHP.setError("Masukkan No. Handphone!!!");
            editTextNoHP.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(txtEmail, txtPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){

                            User user = new User(txtUsername,txtPassword,txtEmail,txtNoHP);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(SignUpActivity.this, "Akun berhasil daftar", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);

                                        startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
                                    }
                                    else{
                                        Toast.makeText(SignUpActivity.this, "Akun gagal daftar", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                        }
                        else {
                            Toast.makeText(SignUpActivity.this, "Pengguna gagal dalam mendaftar", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }

                    }
                });
    }
}