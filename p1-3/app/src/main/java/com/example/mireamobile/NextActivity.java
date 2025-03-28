package com.example.mireamobile;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_next);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView messageText = findViewById(R.id.outputWindow);

        StudentData data = (StudentData) getIntent().getSerializableExtra("data");

        if (data == null)
        {
            Log.e("NextActivity", "data is null");
            return;
        }

        String fio = data.getName();
        String group = data.getGroup();
        String age = data.getAge();
        String mark = data.getMark();

        messageText.setText(getString(R.string.user_info, fio, group, age, mark));
    }

    public void back(View view)
    {
        finish();
    }
}