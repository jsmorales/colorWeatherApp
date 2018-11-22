package com.example.johanmorales.colorweatherapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.johanmorales.colorweatherapp.Adapters.MinutelyWeatherAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MinutelyWeatherActivity extends Activity {

    @BindView(R.id.minutelyRecyclerView) RecyclerView minutelyRecyclerView;
    @BindView(R.id.emptyRecyclerTextView) TextView emptyRecyclerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minutely_weather);

        ButterKnife.bind(this);

        ArrayList<Minute> minuteListView = getIntent().getParcelableArrayListExtra("minutes");

        /*for (int i = 0; i < 500; i++){

            Minute minute = new Minute();

            minute.setTitle("17:35");
            minute.setRainProbability("12%");

            minuteListView.add(minute);
        }*/

        //validacion en caso de que no hallan datos que desplegar y no se crashee nuestra app

        if(minuteListView != null && !minuteListView.isEmpty()){

            //The adapter type Recycler
            MinutelyWeatherAdapter minutelyWeatherAdapter = new MinutelyWeatherAdapter(minuteListView, this);

            minutelyRecyclerView.setAdapter(minutelyWeatherAdapter);

            //para que el recycler view sepa como comportarse se crea un LayoutManager
            RecyclerView.LayoutManager managerLayout = new LinearLayoutManager(this);

            //se setea el layoutmanager recien creado
            minutelyRecyclerView.setLayoutManager(managerLayout);

            //esta opcion ajusta los recursos para que sean mejor administrados
            //ayuda a que el recicler trabaje con un mejor rendimiento
            minutelyRecyclerView.setHasFixedSize(true);

            //empty view no visible
            emptyRecyclerTextView.setVisibility(View.INVISIBLE);

        }else{
            //empty view visible
            emptyRecyclerTextView.setVisibility(View.VISIBLE);
        }


    }
}
