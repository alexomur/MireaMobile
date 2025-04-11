package com.example.mireamobile5recycler;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ItemAdapter adapter;
    List<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        items = new ArrayList<>();
        items.add(new Item( R.drawable.apple, "Фрукты"));
        items.add(new Item( R.drawable.carrot, "Овощи"));
        items.add(new Item( R.drawable.cherry, "Ягоды"));

        adapter = new ItemAdapter(items);
        recyclerView.setAdapter(adapter);

        Intent intent = new Intent(this, SpinnerActivity.class);
        startActivity(intent);
    }
}