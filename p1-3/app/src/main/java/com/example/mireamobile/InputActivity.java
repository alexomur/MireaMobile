package com.example.mireamobile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class InputActivity extends AppCompatActivity {

    private EditText fioInput;
    private EditText groupInput;
    private EditText ageInput;
    private EditText markInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_input);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        fioInput = findViewById(R.id.FIO);
        groupInput = findViewById(R.id.group);
        ageInput = findViewById(R.id.age);
        markInput = findViewById(R.id.mark);
    }

    public void save(View v) {
        String fio = fioInput.getText().toString();
        String group = groupInput.getText().toString();
        String age = ageInput.getText().toString();
        String mark = markInput.getText().toString();

        StudentData data = new StudentData(fio, group, age, mark);

        Intent intent = new Intent(this, NextActivity.class);
        intent.putExtra("data", data);
        startActivity(intent);
    }

    public void openMainMenu(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
