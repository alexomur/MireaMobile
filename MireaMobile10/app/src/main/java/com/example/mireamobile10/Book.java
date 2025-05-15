package com.example.mireamobile10;

import java.util.Random;

public class Book {
    private final int id;
    private String name;
    private String author;
    private int year;
    private double price;

    public Book(int id, String name, String author, int year, double price) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
        this.price = price;
    }

    public Book(String name, String author, int year, double price) {
        this(-1, name, author, year, price);
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public void getAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
