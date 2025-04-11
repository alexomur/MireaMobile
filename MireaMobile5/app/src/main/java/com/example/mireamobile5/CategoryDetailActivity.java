package com.example.mireamobile5;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class CategoryDetailActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> items;
    ArrayAdapter<String> adapter;
    EditText inputField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_category_detail);
        String category = getIntent().getStringExtra("category");

        items = new ArrayList<>();
        if ("Фрукты".equals(category))
        {
            items.add("Яблоки");
            items.add("Груши");
            items.add("Апельсины");
        } else if ("Овощи".equals(category))
        {
            items.add("Морковь");
            items.add("Свёкла");
        } else {
            items.add("Арбузы");
            items.add("Виноград");
        }

        listView = findViewById(R.id.itemsListView);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, items);
        listView.setAdapter(adapter);
        inputField = findViewById(R.id.inputField);

        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(v -> {
            String newItem = inputField.getText().toString().trim();
            if (newItem.isEmpty())
                return;

            items.add(newItem);
            adapter.notifyDataSetChanged();
            inputField.setText("");
        });

        Button removeButton = findViewById(R.id.removeButton);
        removeButton.setOnClickListener(v -> {
            SparseBooleanArray checkedItems = listView.getCheckedItemPositions();
            for (int i = checkedItems.size() - 1; i >= 0; i--) {
                if (checkedItems.valueAt(i)) {
                    int position = checkedItems.keyAt(i);
                    items.remove(position);
                }
            }

            adapter.notifyDataSetChanged();
            listView.clearChoices();
        });
    }
}