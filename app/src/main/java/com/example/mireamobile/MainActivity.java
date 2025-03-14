package com.example.mireamobile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final String MAIN_TAG = "Активность Main";

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

        Log.i(MAIN_TAG, "onCreate() - Активность создана");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(MAIN_TAG, "onStart() - Активность становится видимой");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(MAIN_TAG, "onResume() - Активность на переднем плане, с ней можно взаимодействовать");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(MAIN_TAG, "onPause() - Активность теряет фокус, но еще видна (возможно, поверх открыто другое окно)");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(MAIN_TAG, "onStop() - Активность больше не видна (может быть уничтожена)");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(MAIN_TAG, "onDestroy() - Активность уничтожается");
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







