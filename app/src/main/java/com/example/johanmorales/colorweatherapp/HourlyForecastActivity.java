package com.example.johanmorales.colorweatherapp;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.johanmorales.colorweatherapp.Adapters.HourlyWeatherAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HourlyForecastActivity extends Activity {

    @BindView(R.id.hourlyListView) ListView hourlyListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourly_forecast);

        ButterKnife.bind(this);

        ArrayList<Hour> hoursListView = new ArrayList<>();

        for (int i = 0; i < 500; i++){

            Hour hora = new Hour();

            hora.setTitle("13:35");
            hora.setDescription("Sunny");

            hoursListView.add(hora);
        }

        //adapter implementation
        HourlyWeatherAdapter hourlyAdapter = new HourlyWeatherAdapter(hoursListView,this);

        hourlyListView.setAdapter(hourlyAdapter);
    }
}
