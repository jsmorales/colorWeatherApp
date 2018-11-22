package com.example.johanmorales.colorweatherapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

    public static final String TAG = MainActivity.class.getSimpleName(); //gets the name of the app
    //para crear estas constantes desde harcode se pulsa ctr+alt+c y se reemplazan todas las coincidencias
    //que registre el ide.
    public static final String DAILY = "daily";
    public static final String DATA = "data";
    public static final String TIME = "time";
    public static final String SUMMARY = "summary";
    public static final String PRECIP_PROBABILITY = "precipProbability";
    public static final String MINUTELY = "minutely";
    public static final String TIMEZONE = "timezone";

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

    //Arraylist Results
    ArrayList<Day> arrListDays;
    ArrayList<Hour> arrListHours;
    ArrayList<Minute> arrListMinutes;


    private FusedLocationProviderClient mFusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //se le dice a buterknife que haga el bind a esta misma activity
        ButterKnife.bind(this);

        //--------------------------------------------------------------------------------------
        //esta clase lleva como parametro la activity para poder bindear los elementos res
        /*CurrentWeather cu = new CurrentWeather(MainActivity.this);

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
        lowTempTextView.setText(cu.getLowTemp());*/
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

        /*
        * Recuerda que…
            Las imágenes y recursos visuales van dentro de la carpeta drawable.
            Para acceder a los recursos drawable en tus layouts utilizas @drawable/nombre_de_la_imagen.
            Para acceder a los recursos drawable desde tu código utilizas R.drawable.nombre_de_la_imagen.
            Una ImageView contiene imágenes. En tu layout se utiliza el atributo android:src para especificar la imagen que usaras. En tu código utilizas setImageResource() para especificar la imagen
        * */

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


        String APIKEY = "4c6fbf2dde7f441af012c072c04ac356";
        String latitude = "37.8267";
        String longitude = "-122.4233";

        String urlForecast = "https://api.darksky.net/forecast/"+APIKEY+"/"+latitude+","+longitude+"?units=si&lang=es";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, urlForecast, null, new Response.Listener<JSONObject>() {

                    //cuOfJs

                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            CurrentWeather cuOfJsRes = getCurrentWeatherObject(MainActivity.this, response);

                            iconImageView.setImageDrawable(cuOfJsRes.getIconDrawableResource());
                            timeZoneTextView.setText(cuOfJsRes.getTimeZone());
                            descriptionTextView.setText(cuOfJsRes.getDescription());
                            currentTempTextView.setText(cuOfJsRes.getCurrentTemp().toString());
                            highTempTextView.setText(String.format("H: %s°",cuOfJsRes.getHighTemp().toString()));
                            lowTempTextView.setText(String.format("L: %s°",cuOfJsRes.getLowTemp().toString()));


                            arrListDays = getDailyWeatherJson(response);

                            arrListHours = getHourlyWeatherJson(response);

                            arrListMinutes = getMinutelyWeatherJson(response);

                            Toast.makeText(MainActivity.this,"Datos Cargados Correctamente.",Toast.LENGTH_LONG).show();

                            //Parcelables: datos que se pasan por medio de los intents

                        }catch (JSONException error){

                            descriptionTextView.setText(error.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        //Log.e(TAG, error.getMessage());
                        //descriptionTextView.setText(error.getMessage());

                        //despliegue del error en un toast
                        Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();

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

                //parcelable
                dailyActivityIntent.putParcelableArrayListExtra("days",arrListDays);

                startActivity(dailyActivityIntent);
            }
        });

    }

    private CurrentWeather getCurrentWeatherObject (Activity activity, JSONObject response) throws JSONException {

        final CurrentWeather cuOfJs = new CurrentWeather(activity);

        String timeZone = response.getString(TIMEZONE);
        JSONObject currently = response.getJSONObject("currently");
        JSONObject daily = response.getJSONObject(DAILY);
        JSONArray dailyDataArray = daily.getJSONArray(DATA);
        JSONObject dailyToday = dailyDataArray.getJSONObject(0);

        cuOfJs.setDescription(currently.getString(SUMMARY));
        cuOfJs.setIconImage(currently.getString("icon"));
        //en caso de necesitar redondear la cantidad se utiliza Math.round(int);
        cuOfJs.setCurrentTemp((double) Math.round(currently.getDouble("temperature")));
        cuOfJs.setHighTemp((double) Math.round(dailyToday.getDouble("temperatureHigh")));
        cuOfJs.setLowTemp((double) Math.round(dailyToday.getDouble("temperatureLow")));
        cuOfJs.setTimeZone(timeZone);

        return cuOfJs;
    }

    //Metodo que retorna un array list de dias
    private ArrayList<Day> getDailyWeatherJson(JSONObject response) throws JSONException{

        DateFormat dateFormat = new SimpleDateFormat("EEEE"); //este es el patron de dia (SimpleDateFormat)

        final ArrayList<Day> dayArrayResponseJs = new ArrayList<>();

        JSONObject daily = response.getJSONObject(DAILY);

        JSONArray dailyArray = daily.getJSONArray(DATA);

        for (int i = 0; i < dailyArray.length(); i++){

            JSONObject dayPosition = dailyArray.getJSONObject(i);

            Day day = new Day();

            day.setDayName(dateFormat.format(dayPosition.getLong(TIME) * 1000).toUpperCase());
            day.setDescription(dayPosition.getString(SUMMARY));
            day.setProbability("Rain Probability: "+(Double.valueOf(dayPosition.getString(PRECIP_PROBABILITY))*100)+"%");

            dayArrayResponseJs.add(day);
        }

        return dayArrayResponseJs;
    }

    private ArrayList<Hour> getHourlyWeatherJson(JSONObject response) throws JSONException{

        String timeZone = response.getString(TIMEZONE);

        DateFormat dateFormatHour = new SimpleDateFormat("HH:mm");
        //setear la timezone que indica la API
        dateFormatHour.setTimeZone(TimeZone.getTimeZone(timeZone));

        ArrayList<Hour> hourArrList = new ArrayList<>();

        JSONObject hourly = response.getJSONObject("hourly");
        JSONArray hourlyArray = hourly.getJSONArray(DATA);

        for (int i = 0; i < hourlyArray.length(); i++){

            Hour hour = new Hour();

            JSONObject hourJson = hourlyArray.getJSONObject(i);

            hour.setTitle(dateFormatHour.format(hourJson.getLong(TIME)*1000));
            hour.setDescription(hourJson.getString(SUMMARY));

            hourArrList.add(hour);
        }

        return hourArrList;
    }

    private ArrayList<Minute> getMinutelyWeatherJson(JSONObject response) throws JSONException{

        String timeZone = response.getString(TIMEZONE);

        DateFormat dateFormatHour = new SimpleDateFormat("HH:mm");
        //setear la timezone que indica la API
        dateFormatHour.setTimeZone(TimeZone.getTimeZone(timeZone));

        ArrayList<Minute> minuteArrList = new ArrayList<>();

        JSONObject minutely = response.getJSONObject(MINUTELY);
        JSONArray minutelyArray = minutely.getJSONArray(DATA);

        for (int i = 0; i < minutelyArray.length(); i++){

            Minute minute = new Minute();

            JSONObject minuteJson = minutelyArray.getJSONObject(i);

            minute.setTitle(dateFormatHour.format(minuteJson.getLong(TIME)*1000));
            minute.setRainProbability("Rain Probability: "+(Double.valueOf(minuteJson.getString(PRECIP_PROBABILITY))*100)+"%");
            //minute.setRainProbability("Rain Probability: "+minuteJson.getString(PRECIP_PROBABILITY));

            minuteArrList.add(minute);
        }

        return minuteArrList;
    }

    //forma mas practica de añadir eventos click a los elementos

    @OnClick(R.id.btnMenu2TextView)
    public void hourlyForecastClick (){

        Intent hourlyActivityIntent = new Intent(MainActivity.this,HourlyForecastActivity.class);

        //en este intent se añade un extra llamado socialNumber con el valor de tipo int
        hourlyActivityIntent.putExtra("SocialNumber",1024524163);

        hourlyActivityIntent.putParcelableArrayListExtra("hours",arrListHours);

        startActivity(hourlyActivityIntent);
    }

    @OnClick(R.id.btnMenu3TextView)
    public void minutelyWeatherClick (){

        Intent minutelyActivityIntent = new Intent(MainActivity.this,MinutelyWeatherActivity.class);
        minutelyActivityIntent.putParcelableArrayListExtra("minutes",arrListMinutes);
        startActivity(minutelyActivityIntent);
    }
}
