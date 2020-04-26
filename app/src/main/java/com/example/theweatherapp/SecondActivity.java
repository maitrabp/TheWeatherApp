package com.example.theweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SecondActivity extends AppCompatActivity {

    private TextView result;
    private TextView cityTitle;
    private Button backButton;
    private RequestQueue requestQueue;
    String url = "";
    private String city = "London";
    int dayCounter = 1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        result = findViewById(R.id.result);
        cityTitle = findViewById(R.id.cityTitle);
        backButton = findViewById(R.id.backButton);
        final Intent intent = getIntent();
        city = intent.getStringExtra("cityKey");
        cityTitle.setText(city);

        url = "https://api.openweathermap.org/data/2.5/forecast?q=" + city + "&appid=6e1fefd6dbfde355732249beae1fd0d1";
        //instantiate the request queue
        requestQueue = Volley.newRequestQueue(this);
        //create object request
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("JSON response", response.toString());
                        try {
                            result.setText("");
                            JSONArray list = response.getJSONArray("list");
                            for(int i = 0; i < list.length(); i+=8)
                            {
                                JSONObject beforeWeather = list.getJSONObject(i);
                                JSONArray weather = beforeWeather.getJSONArray("weather");
                                JSONObject wind = beforeWeather.getJSONObject("wind");
                                JSONObject main = beforeWeather.getJSONObject("main");
                                JSONObject currentweather = weather.getJSONObject(0);

                                Log.e("JSonObject", "onResponse: " +
                                        " "+currentweather.getString("main") +
                                        " "+wind.getString("speed") +
                                        " "+main.getString("temp") +
                                        " "+main.getString("humidity"));
                                String description = currentweather.getString("main");
                                String windSpeed = wind.getString("speed");
                                double temp = main.getDouble("temp");
                                double actualTemp = KtoF(temp);
                                String humidity = main.getString("humidity");
                                result.append("DAY " + dayCounter + ": \n Description: " + description + "\t\t WindSpeed: " + windSpeed + " m/sec\n Temperature: " + String.valueOf(Math.round(actualTemp)) + " F\t\t Humidity: " + humidity + "%\n\n");
                                dayCounter++;
                            }
                            intent.setData(null);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
        requestQueue.add(jsonObjectRequest);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(myIntent);
            }
        });
    } //end of oncreate

    //Method to convert Kelvin to Fahrenheit
    public double KtoF(double inK)
    {
        double F = ((inK-273.15) * (9.0/5.0) + 32);

        return F;
    }
}
