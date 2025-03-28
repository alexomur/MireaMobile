package com.example.mireamobile4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText firstnameView;
    private EditText lastnameView;

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

        firstnameView = findViewById(R.id.input_firstname);
        lastnameView = findViewById(R.id.input_lastname);
    }

    public void next(View view)
    {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("firstname", firstnameView.getText().toString());
        intent.putExtra("lastname", lastnameView.getText().toString());
        startActivity(intent);
    }
}