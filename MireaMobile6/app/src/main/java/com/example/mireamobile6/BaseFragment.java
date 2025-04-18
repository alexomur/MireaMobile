package com.example.mireamobile6;

import android.os.Bundle;

import androidx.annotation.DrawableRes;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public abstract class BaseFragment extends Fragment {

    protected abstract @DrawableRes int getImageResource();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        ImageView imageView = view.findViewById(R.id.imageView);
        imageView.setImageResource(getImageResource());
        return view;
    }
}