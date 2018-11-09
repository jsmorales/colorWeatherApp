package com.example.johanmorales.colorweatherapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

    public static final String TAG = MainActivity.class.getSimpleName(); //gets the name of the app

    private TextView dailyButton;

    //ligando los elementos de la UI con butternkife
    @BindView(R.id.iconImageView) ImageView iconImageView;
    @BindView(R.id.descriptionTextView) TextView descriptionTextView;
    @BindView(R.id.currentTempTextView) TextView currentTempTextView;
    @BindView(R.id.highTempTextView) TextView highTempTextView;
    @BindView(R.id.lowTempTextView) TextView lowTempTextView;
    @BindView(R.id.timeZoneTextView) TextView timeZoneTextView;

    //los drawables son los recursos como imagenes que se encuentran en la carpeta res
    //añadiendo un drawable con butterknife
    @BindDrawable(R.drawable.clear_night) Drawable clearNigthDrawable;

    //with butterKnife forma de asignar una view con un elemento
    //@BindView(R.id.btnMenu2TextView) TextView hourlyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //se le dice a buterknife que haga el bind a esta misma activity
        ButterKnife.bind(this);

        //--------------------------------------------------------------------------------------
        //esta clase lleva como parametro la activity para poder bindear los elementos res
        CurrentWeather cu = new CurrentWeather(MainActivity.this);

        final CurrentWeather cuOfJs = new CurrentWeather(MainActivity.this);

        cu.setDescription("Clear Night");
        cu.setCurrentTemp("14");
        cu.setHighTemp("H:18°");
        cu.setLowTemp("L:9°");
        cu.setIconImage("clear-night");


        //para un image view se setea un imagedrawable
        iconImageView.setImageDrawable(cu.getIconDrawableResource());

        descriptionTextView.setText(cu.getDescription());
        currentTempTextView.setText(cu.getCurrentTemp());
        highTempTextView.setText(cu.getHighTemp());
        lowTempTextView.setText(cu.getLowTemp());
        //--------------------------------------------------------------------------------------

        //Volley ejemplo peticion http con android
        //--------------------------------------------------------------------------------------

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        /*Ejemplo de
        String url ="http://www.google.com";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG,"The response is: "+response);
                        // Display the first 500 characters of the response string.
                        descriptionTextView.setText("Response is: "+ response.substring(0,5));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                descriptionTextView.setText("That didn't work!");
            }
        });*/

        String urlForecast = "https://api.darksky.net/forecast/4c6fbf2dde7f441af012c072c04ac356/37.8267,-122.4233";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, urlForecast, null, new Response.Listener<JSONObject>() {

                    //cuOfJs

                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            String timeZone = response.getString("timezone");
                            JSONObject currently = response.getJSONObject("currently");
                            JSONObject daily = response.getJSONObject("daily");
                            JSONArray dailyDataArray = daily.getJSONArray("data");
                            JSONObject dailyToday = dailyDataArray.getJSONObject(0);

                            cuOfJs.setDescription(currently.getString("summary"));
                            cuOfJs.setIconImage(currently.getString("icon"));
                            cuOfJs.setCurrentTemp(currently.getString("temperature"));
                            cuOfJs.setHighTemp(dailyToday.getString("temperatureHigh"));
                            cuOfJs.setLowTemp(dailyToday.getString("temperatureLow"));
                            cuOfJs.setTimeZone(timeZone);

                            iconImageView.setImageDrawable(cuOfJs.getIconDrawableResource());
                            timeZoneTextView.setText(cuOfJs.getTimeZone());
                            descriptionTextView.setText(cuOfJs.getDescription());
                            currentTempTextView.setText(cuOfJs.getCurrentTemp());
                            highTempTextView.setText(cuOfJs.getHighTemp());
                            lowTempTextView.setText(cuOfJs.getLowTemp());

                        }catch (JSONException error){

                            timeZoneTextView.setText(error.toString());
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });

        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);
        //--------------------------------------------------------------------------------------

        //forma tradicional de trabajar los eventos click
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

    //forma mas practica de añadir eventos click a los elementos

    @OnClick(R.id.btnMenu2TextView)
    public void hourlyForecastClick (){

        Intent hourlyActivityIntent = new Intent(MainActivity.this,HourlyForecastActivity.class);
        startActivity(hourlyActivityIntent);
    }

    @OnClick(R.id.btnMenu3TextView)
    public void minutelyWeatherClick (){

        Intent minutelyActivityIntent = new Intent(MainActivity.this,MinutelyWeatherActivity.class);
        startActivity(minutelyActivityIntent);
    }
}
