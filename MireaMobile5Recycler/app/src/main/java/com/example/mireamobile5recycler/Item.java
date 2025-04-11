package com.example.mireamobile5recycler;

public class Item {

    private int imageResId;
    private String title;

    public Item(int imgRes, String txt)
    {
        imageResId = imgRes;
        title = txt;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getTitle() {
        return title;
    }
}
