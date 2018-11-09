package com.example.johanmorales.colorweatherapp;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import butterknife.BindDrawable;
import butterknife.ButterKnife;

public class CurrentWeather {

    private String iconImage;
    private String description;
    private String currentTemp;
    private String highTemp;
    private String lowTemp;
    private String timeZone;



    public static final String CLEAR_NIGHT = "clear-night";
    public static final String CLEAR_DAY = "clear-day";
    public static final String CLOUDY = "cloudy";
    public static final String PARTLY_CLOUDY_NIGHT = "partly-cloudy-night";
    public static final String FOG = "fog";
    public static final String NA = "na";
    public static final String PARTLY_CLOUDY_DAY = "partly-cloudy-day";
    public static final String RAIN = "rain";
    public static final String SLEET = "sleet";
    public static final String SNOW = "snow";
    public static final String SUNNY = "sunny";
    public static final String WIND = "wind";


    @BindDrawable(R.drawable.clear_night) Drawable clearNight;
    @BindDrawable(R.drawable.clear_day) Drawable clearDay;
    @BindDrawable(R.drawable.cloudy) Drawable cloudy;
    @BindDrawable(R.drawable.cloudy_night) Drawable cloudyNight;
    @BindDrawable(R.drawable.fog) Drawable fog;
    @BindDrawable(R.drawable.na) Drawable na;
    @BindDrawable(R.drawable.partly_cloudy) Drawable partlyCloudy;
    @BindDrawable(R.drawable.rain) Drawable rain;
    @BindDrawable(R.drawable.sleet) Drawable sleet;
    @BindDrawable(R.drawable.snow) Drawable snow;
    @BindDrawable(R.drawable.sunny) Drawable sunny;
    @BindDrawable(R.drawable.wind) Drawable wind;

    //constructor que bindea los resources para butterknife
    public CurrentWeather(Activity activity){

        //se bindea esta clase con la actividad desde donde se esta instanciando
        ButterKnife.bind(this, activity);
    }

    public String getIconImage() {
        return iconImage;
    }

    public void setIconImage(String iconImage) {
        this.iconImage = iconImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrentTemp() {
        return currentTemp;
    }

    public void setCurrentTemp(String currentTemp) {
        this.currentTemp = currentTemp;
    }

    public String getHighTemp() {
        return highTemp;
    }

    public void setHighTemp(String highTemp) {
        this.highTemp = highTemp;
    }

    public String getLowTemp() {
        return lowTemp;
    }

    public void setLowTemp(String lowTemp) {
        this.lowTemp = lowTemp;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    //return the drawable
    public Drawable getIconDrawableResource(){
        //retorna el drawable dependiendo del valor de la variable iconImage de tipo String
        switch (iconImage) {
            case CLEAR_NIGHT:
                return clearNight;
            case CLEAR_DAY:
                return clearDay;
            case CLOUDY:
                return cloudy;
            case PARTLY_CLOUDY_NIGHT:
                return cloudyNight;
            case FOG:
                return fog;
            case NA:
                return na;
            case PARTLY_CLOUDY_DAY:
                return partlyCloudy;
            case RAIN:
                return rain;
            case SLEET:
                return sleet;
            case SNOW:
                return snow;
            case SUNNY:
                return sunny;
            case WIND:
                return wind;
            default:
                return na;

        }
    }

}
