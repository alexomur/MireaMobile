package com.example.mireamobile6;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

public class SecondActivity extends AppCompatActivity {

    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        actionBar = getSupportActionBar();
        if (actionBar == null) {
            return;
        }

        actionBar.setDisplayHomeAsUpEnabled(true);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        setAppState(R.string.black_holes, new BlackHolesFragment());

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_black_holes)
                setAppState(R.string.black_holes, new BlackHolesFragment());
            else if (itemId == R.id.nav_cats_cradle)
                setAppState(R.string.cats_cradle, new CatsCradleFragment());
            else if (itemId == R.id.nav_invent_everything)
                setAppState(R.string.how_to_invent_everything, new InventEverythingFragment());

            return true;
        });
    }

    private void setAppState(int titleResId, Fragment fragment) {
        actionBar.setTitle(titleResId);
        getSupportFragmentManager().beginTransaction().replace(R.id.second_activity_fragment_container, fragment).commit();
    }

    @Override
    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}