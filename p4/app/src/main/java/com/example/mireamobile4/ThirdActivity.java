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

public class ThirdActivity extends AppCompatActivity {

    EditText dateView;
    EditText timeView;
    EditText commentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_third);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dateView = findViewById(R.id.input_date);
        timeView = findViewById(R.id.input_time);
        commentView = findViewById(R.id.input_comment);
    }

    public void next(View view)
    {
        String date = dateView.getText().toString();
        String time = timeView.getText().toString();
        String comment = commentView.getText().toString();
        String result = date + " " + time + " " + comment;

        Intent resultIntent = new Intent();

        resultIntent.putExtra("result", result);
        setResult(RESULT_OK, resultIntent);

        finish();
    }
}