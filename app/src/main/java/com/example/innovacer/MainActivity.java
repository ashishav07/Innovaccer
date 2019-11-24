package com.example.innovacer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawer;
    private NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawer = findViewById(R.id.drawer_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);  //Home button is default
        onNavigationItemSelected(navigationView.getMenu().getItem(0));

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.home:
                navigationView.getMenu().getItem(0).setChecked(true);
                navigationView.getMenu().getItem(1).setChecked(false);
                navigationView.getMenu().getItem(2).setChecked(false);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
                break;
            case R.id.current:
                navigationView.getMenu().getItem(1).setChecked(true);
                navigationView.getMenu().getItem(0).setChecked(false);
                navigationView.getMenu().getItem(2).setChecked(false);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new CurrentFragment()).commit();
                break;
            case R.id.history:
                navigationView.getMenu().getItem(2).setChecked(true);
                navigationView.getMenu().getItem(1).setChecked(false);
                navigationView.getMenu().getItem(0).setChecked(false);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HistoryFragment()).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
