package com.example.mireamobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class InputActivity extends AppCompatActivity {

    private EditText userInput;
    private TextView textViewList;
    private List<String> inputHistory;

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

        inputHistory = new ArrayList<>();

        textViewList = findViewById(R.id.textViewList);
        userInput = findViewById(R.id.userInput);
    }

    public void save(View v) {
        String text = userInput.getText().toString().trim();

        if (!text.isEmpty()) {
            inputHistory.add(text);

            StringBuilder sb = new StringBuilder();
            for (String s : inputHistory) {
                sb.append(s).append("\n");
            }

            textViewList.setText(sb.toString());

            userInput.setText("");
        }
    }

    public void openMainMenu(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
