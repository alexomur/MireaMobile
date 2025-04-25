package com.example.mireamobile7;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button startBtn, stopBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBtn = findViewById(R.id.button_start);
        stopBtn = findViewById(R.id.button_stop);

        startBtn.setOnClickListener(v ->
                startService(new Intent(MainActivity.this, SimpleService.class)));

        stopBtn.setOnClickListener(v ->
                stopService(new Intent(MainActivity.this, SimpleService.class)));
    }
}