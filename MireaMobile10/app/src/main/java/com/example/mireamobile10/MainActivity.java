package com.example.mireamobile10;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText inputName, inputAuthor, inputYear, inputPrice;
    TextView outputBooks;
    Button buttonAdd, buttonShowAll;
    BookDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new BookDatabaseHelper(this);
        inputName = findViewById(R.id.inputName);
        inputAuthor = findViewById(R.id.inputAuthor);
        inputYear = findViewById(R.id.inputYear);
        inputPrice = findViewById(R.id.inputPrice);
        buttonAdd       = findViewById(R.id.buttonAdd);
        buttonShowAll   = findViewById(R.id.buttonShowAll);
        outputBooks = findViewById(R.id.outputBooks);

        buttonAdd.setOnClickListener(v -> {
            String name = inputName.getText().toString();
            String author = inputAuthor.getText().toString();
            int year = Integer.parseInt(inputYear.getText().toString());
            double price = Double.parseDouble(inputPrice.getText().toString());

            dbHelper.insertBook(new Book(name, author, year, price));
            Toast.makeText(this, "Добавлено", Toast.LENGTH_LONG).show();
        });

        buttonShowAll.setOnClickListener(v -> {
            Cursor cursor = dbHelper.getAllBooks();
            StringBuilder builder = new StringBuilder();

            while (cursor.moveToNext()) {
                builder.append("ID: ").append(cursor.getInt(0)).append("\n")
                        .append("Название: ").append(cursor.getString(1)).append("\n")
                        .append("Автор: ").append(cursor.getString(2)).append("\n")
                        .append("Год: ").append(cursor.getInt(3)).append("\n")
                        .append("Цена: ").append(cursor.getDouble(4)).append("\n\n");
                outputBooks.setText(builder.toString());
            }
        });
    }
}