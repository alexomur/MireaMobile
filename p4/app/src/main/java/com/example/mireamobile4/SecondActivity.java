package com.example.mireamobile4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    private TextView dateView;
    private TextView timeView;

    private final ActivityResultLauncher<Intent> thirdActivityLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                Intent data = result.getData();

                if (result.getResultCode() != RESULT_OK || data == null)
                {
                    Log.e("SecondActivity", "result code (" + result.getResultCode() +
                            ") is not OK or result data is null (" + (result.getData() == null) + "!");
                    return;
                }

                String date = data.getStringExtra("date");
                String time = data.getStringExtra("time");

                dateView.setText(date);
                timeView.setText(time);
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle extras = getIntent().getExtras();
        if (extras == null)
        {
            Log.e("SecondActivity", "extras is null!");
            return;
        }

        String firstname = extras.getString("firstname");
        String lastname = extras.getString("lastname");

        ((TextView)(findViewById(R.id.text_firstname))).setText(firstname);
        ((TextView)(findViewById(R.id.text_lastname))).setText(lastname);

        dateView = findViewById(R.id.text_date);
        timeView = findViewById(R.id.text_time);
    }

    public void next(View view)
    {
        Intent intent = new Intent(this, ThirdActivity.class);
        thirdActivityLauncher.launch(intent);
    }
}