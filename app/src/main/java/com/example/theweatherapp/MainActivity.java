package com.example.theweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<CityItem> mCityList;
    private CityAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initList();

        Spinner spinnerCities = findViewById(R.id.mainSpinner);
        mAdapter = new CityAdapter(this, mCityList);
        spinnerCities.setAdapter(mAdapter);

        spinnerCities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CityItem itemClicked = (CityItem) parent.getItemAtPosition(position);
                String clickedCityName = itemClicked.getCityName();
                Intent i = new Intent(getApplicationContext(), SecondActivity.class);
                if(clickedCityName.equals("London"))
                {
                    i.putExtra("cityKey", clickedCityName);
                    startActivity(i);
                }
                else if(clickedCityName.equals("Delhi"))
                {
                    i.putExtra("cityKey", clickedCityName);
                    startActivity(i);
                }
                else if(clickedCityName.equals("Chicago"))
                {
                    i.putExtra("cityKey", clickedCityName);
                    startActivity(i);
                }
                else if(clickedCityName.equals("Detroit"))
                {
                    i.putExtra("cityKey", clickedCityName);
                    startActivity(i);
                }
                else if(clickedCityName.equals("Dubai"))
                {
                    i.putExtra("cityKey", clickedCityName);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(MainActivity.this, clickedCityName + " selected", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    private void initList()
    {
        mCityList = new ArrayList<>();
        mCityList.add(new CityItem("Select a city"));
        mCityList.add(new CityItem("London"));
        mCityList.add(new CityItem("Delhi"));
        mCityList.add(new CityItem("Chicago"));
        mCityList.add(new CityItem("Detroit"));
        mCityList.add(new CityItem("Dubai"));
    }
}
