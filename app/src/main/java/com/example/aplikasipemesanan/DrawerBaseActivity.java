//package com.example.aplikasipemesanan;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//
//public class DrawerBaseActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_drawer_base);
//    }
//}

package com.example.aplikasipemesanan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class DrawerBaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;

    @Override
    public void setContentView(View view) {
        drawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_drawer_base, null);
        FrameLayout container = drawerLayout.findViewById(R.id.activityContainer);
        container.addView(view);
        super.setContentView(drawerLayout);

        Toolbar toolbar = drawerLayout.findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = drawerLayout.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar, R.string.menu_drawer_open, R.string.menu_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);

        switch (item.getItemId()) {
            case R.id.nav_info:
                startActivity(new Intent(this,InfoTermActivity.class));
                overridePendingTransition(0, 0);
                break;

            case R.id.nav_order:
                startActivity(new Intent(this,OrderActivity.class));
                overridePendingTransition(0, 0);
                break;

            case R.id.nav_dashboard:
                startActivity(new Intent(this,DashboardActivity.class));
                overridePendingTransition(0, 0);
                break;

            case R.id.nav_menu:
                startActivity(new Intent(this,MenuActivity.class));
                overridePendingTransition(0, 0);
                break;

            case R.id.nav_signout:
                FirebaseAuth.getInstance().signOut();
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Ingin keluar dari akun???");
                builder.setCancelable(false);
                builder.setPositiveButton(" Iya ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        moveTaskToBack(true);
                        startActivity(new Intent(DrawerBaseActivity.this, WelcomeActivity.class));
                        Toast.makeText(DrawerBaseActivity.this, "Pengguna telah Keluar", Toast.LENGTH_LONG).show();
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
                overridePendingTransition(0, 0);
                break;
        }
        return false;
    }

    protected void allocateActivityTitle(String titleString){
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(titleString);
        }
    }

}