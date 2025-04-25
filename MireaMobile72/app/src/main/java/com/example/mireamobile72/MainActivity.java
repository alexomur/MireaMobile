package com.example.mireamobile72;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView txtResult;
    Calendar calendar = Calendar.getInstance();
    public static final int REQUEST_CODE_CUSTOM = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAlert = findViewById(R.id.btnAlert);
        Button btnDate = findViewById(R.id.btnDate);
        Button btnTime = findViewById(R.id.btnTime);
        Button btnCustom = findViewById(R.id.btnCustom);

        txtResult = findViewById(R.id.txtResult);

        btnAlert.setOnClickListener(v -> showAlertDialog());
        btnDate.setOnClickListener(v -> showDatePicker());
        btnTime.setOnClickListener(v -> showTimePicker());
        btnCustom.setOnClickListener(v -> {
            Intent intent = new Intent(this, CustomDialogActivity.class);
            startActivityForResult(intent, REQUEST_CODE_CUSTOM);
        });
    }

    private void showAlertDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Предупреждение")
                .setMessage("Вы уверены, что хотите продолжить?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("Да", ((dialog, which) ->
                        txtResult.setText("Нажато: Да")))
                .setNegativeButton("Нет", ((dialog, which) ->
                        txtResult.setText("Нажато: Нет")))
                .show();
    }

    private void showDatePicker() {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        new DatePickerDialog(this, (view, y, m, d) -> {
            String date = d + "/" + (m + 1) + "/" + y;
            txtResult.setText("Выбрана дата: " + date);
        }, year, month, day).show();
    }

    private void showTimePicker() {
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        new TimePickerDialog(this, (view, h, m) -> {
            String time = String.format(Locale.getDefault(), "%02d:%02d", h, m);
            txtResult.setText("Выбрано время: " + time);
        }, hour, minute, true).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CUSTOM && resultCode == RESULT_OK && data != null)
        {
            String name = data.getStringExtra("name");
            txtResult.setText("Введено имя: " + name);
        }
    }
}