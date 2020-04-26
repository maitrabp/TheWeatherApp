package com.example.theweatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CityAdapter extends ArrayAdapter<CityItem> {

    public CityAdapter(Context context, ArrayList<CityItem> cityList){
        super(context, 0, cityList);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent)
    {
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.city_spinner_row, parent,
                    false);
        }

        TextView textviewName = convertView.findViewById(R.id.spinner_row_textview);
        CityItem currentItem = getItem(position);

        if(currentItem!=null) {
            textviewName.setText(currentItem.getCityName());
        }
        return convertView;
    }
}
