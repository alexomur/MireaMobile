package com.example.mireamobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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
    }

    public void openNameActivity(View v) {
        Intent intent = new Intent(this, NameActivity.class);
        startActivity(intent);
    }

    public void openGalleryActivity(View v) {
        Intent intent = new Intent(this, GalleryActivity.class);
        startActivity(intent);
    }

    public void openInputActivity(View v) {
        Intent intent = new Intent(this, InputActivity.class);
        startActivity(intent);
    }
}