package com.example.johanmorales.colorweatherapp;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.johanmorales.colorweatherapp.Adapters.HourlyWeatherAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HourlyForecastActivity extends Activity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.hourlyListView) ListView hourlyListView;
    @BindView(R.id.emptyTextView) TextView emptyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourly_forecast);

        ButterKnife.bind(this);

        //prueba de intent, como pasar informacion de una actividad a otra
        int socialNumber = getIntent().getIntExtra("SocialNumber",0);

        Log.d(TAG,"El socialNumber es: "+socialNumber);

        ArrayList<Hour> hoursListView = getIntent().getParcelableArrayListExtra("hours");


        //Log.d(TAG,"NULL HORAS: "+String.valueOf(hoursListView == null));

        /*for (int i = 0; i < 500; i++){

            Hour hora = new Hour();

            hora.setTitle("13:35");
            hora.setDescription("Sunny");

            hoursListView.add(hora);
        }*/

        //adapter implementation
        HourlyWeatherAdapter hourlyAdapter = new HourlyWeatherAdapter(hoursListView,this);

        hourlyListView.setAdapter(hourlyAdapter);
        //setear la view de empty
        hourlyListView.setEmptyView(emptyTextView);
    }
}
