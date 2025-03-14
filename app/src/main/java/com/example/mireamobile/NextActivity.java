package com.example.mireamobile;

import android.os.Bundle;
import android.util.Log;
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

        TextView messageText = new TextView(this);
        messageText.setTextSize(26);
        messageText.setPadding(16, 16, 16, 16);

        Bundle args = getIntent().getExtras();
        if (args == null)
        {
            Log.e("NextActivity", "args is null");
            return;
        }

        String fio = args.getString("FIO");
        String group = args.getString("group");
        int age = args.getInt("age");
        int mark = args.getInt("mark");

        messageText.setText(getString(R.string.user_info, fio, group, age, mark));

        setContentView(messageText);
    }
}