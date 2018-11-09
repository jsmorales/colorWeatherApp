package com.example.johanmorales.colorweatherapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

    public static final String TAG = MainActivity.class.getSimpleName(); //gets the name of the app

    private TextView dailyButton;

    //with butterKnife forma de asignar una view con un elemento
    //@BindView(R.id.btnMenu2TextView) TextView hourlyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //se le dice a buterknife que haga el bind a esta misma activity
        ButterKnife.bind(this);

        dailyButton = findViewById(R.id.btnMenu1TextView);

        dailyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d(TAG, "Presionando daily button!!");
                //se crea el intent desde que actividad esta y la actividad para donde va en este caso
                //pasa de la MainActivity.this a DailyWeatherActivity.class
                Intent dailyActivityIntent = new Intent(MainActivity.this,DailyWeatherActivity.class);
                startActivity(dailyActivityIntent);
            }
        });

    }

    @OnClick(R.id.btnMenu2TextView)
    public void hourlyForecastClick (){

        Intent hourlyActivityIntent = new Intent(MainActivity.this,HourlyForecastActivity.class);
        startActivity(hourlyActivityIntent);
    }
}
