package com.example.aplikasipemesanan;

public class User {

    public String Username;
    public String password;
    public String email;
    public String NoHP;

    public User() {

    }

    public User (String Username, String password, String email, String NoHP){
        this.Username = Username;
        this.password = password;
        this.email = email;
        this.NoHP = NoHP;
    }
}
