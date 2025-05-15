package com.example.mireamobile10;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BookDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "book_shop.db";
    private static final int DB_VERSION = 1;

    public BookDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE books (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "author TEXT," +
                "year INTEGER," +
                "price REAL)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS books");
        onCreate(db);
    }

    public void insertBook(Book book) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", book.getName());
        values.put("author", book.getAuthor());
        values.put("year", book.getYear());
        values.put("price", book.getPrice());

        db.insert("books", null, values);
    }

    public Cursor getAllBooks() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM books", null);
    }

    public void updateBooks(Book book) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", book.getName());
        values.put("author", book.getAuthor());
        values.put("year", book.getYear());
        values.put("price", book.getPrice());

        db.update("cars",
                values, "id = ?",
                new String[]{String.valueOf(book.getId())});
    }

    public void deleteBook(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("books",
                "id = ?",
                new String[]{String.valueOf(id)});
    }
}
