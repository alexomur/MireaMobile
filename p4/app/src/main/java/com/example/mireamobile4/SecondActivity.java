package com.example.mireamobile4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    private EditText subjectEditText;
    private TextView subjectText;

    private final ActivityResultLauncher<Intent> thirdActivityLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                Intent info = result.getData();

                if (result.getResultCode() != RESULT_OK || info == null)
                {
                    Log.e("SecondActivity", "result code (" + result.getResultCode() +
                            ") is not OK or result info is null (" + (info == null) + ")!");
                    return;
                }

                String subjectInfo = info.getStringExtra("result");

                if (subjectInfo == null)
                {
                    Log.e("SecondActivity", "No extra for subjectInfo was found");
                    return;
                }

                Log.d("SecondActivity", subjectInfo);

                Toast.makeText(this, "Время занятия передано успешно", Toast.LENGTH_LONG).show();

                String subject = subjectEditText.getText().toString();

                String finalInfo = subject + ": " + subjectInfo;

                Log.d("SecondActivity", finalInfo);

                subjectText.setText(finalInfo);
            });

    public void next(View view)
    {
        Intent intent = new Intent(this, ThirdActivity.class);
        thirdActivityLauncher.launch(intent);
    }

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

        subjectEditText = findViewById(R.id.input_subject);
        subjectText = findViewById(R.id.text_subject);
    }
}




