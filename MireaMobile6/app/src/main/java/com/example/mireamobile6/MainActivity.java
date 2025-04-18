package com.example.mireamobile6;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.metro_series);
        }

        drawerLayout = findViewById(R.id.main);
        NavigationView navigationView = findViewById(R.id.drawer_navigation_view);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_navmenu, R.string.close_navmenu);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        setAppState(R.string.metro_2033, new Metro2033Fragment());

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.nav_metro_2033)
                    setAppState(R.string.metro_2033, new Metro2033Fragment());
                else if (id == R.id.nav_metro_2034)
                    setAppState(R.string.metro_2034, new Metro2034Fragment());
                else if (id == R.id.nav_metro_2035)
                    setAppState(R.string.metro_2035, new Metro2035Fragment());
                else if (id == R.id.nav_second)
                    startActivity(new Intent(MainActivity.this, SecondActivity.class));

                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setAppState(int subtitleResId, Fragment fragment) {
        actionBar.setSubtitle(subtitleResId);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_activity_fragment_container, fragment).commit();
    }
}
























