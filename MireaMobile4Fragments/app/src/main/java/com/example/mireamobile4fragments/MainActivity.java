package com.example.mireamobile4fragments;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.fragmentContainerView, fragment);
        transaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button firstButton = findViewById(R.id.firstButton);
        Button secondButton = findViewById(R.id.secondButton);
        Button thirdButton = findViewById(R.id.thirdButton);

        firstButton.setOnClickListener(v -> replaceFragment(new FirstFragment()));
        secondButton.setOnClickListener(v -> replaceFragment(new SecondFragment()));
        thirdButton.setOnClickListener(v -> replaceFragment(new ThirdFragment()));

        if (savedInstanceState == null) {
            replaceFragment(new FirstFragment());
        }
    }
}