package com.example.johanmorales.colorweatherapp;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.johanmorales.colorweatherapp.Adapters.DailyWeatherAdapter;

import java.util.ArrayList;

/*
* Esta clase era una activity normal pero se paso a ListActivity ya que esta clase
* ya tiene definidos los metodos que se van a utilizar.
*
* el ListView del layout debe llevar el id --> android:id="@android:id/list"
* */

public class DailyWeatherActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_weather);

        //En un ArrayList se agregan los dias de la semana

        ArrayList<Day> daysArray = getIntent().getParcelableArrayListExtra("days");

        /*for (int i = 0; i < 500; i++){

            Day dia = new Day();

            dia.setDayName("Lunes");
            dia.setDescription("Parcialmente soleado.");
            dia.setProbability("Lluvia del 20%");

            daysArray.add(dia);
        }*/

        DailyWeatherAdapter diaAdaptador = new DailyWeatherAdapter(daysArray, this);
        setListAdapter(diaAdaptador);

        //teniendo los datos en el arraylist usamos el adapter para mostrarlo en nuestra ListView
        //del layout
        //se pasan los parametros this( esta lista), la ListView en el layout, y el array con los datos a listar
        //ArrayAdapter<String> dataArrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,daysArray);
        //por ultimo se setea el list adapter que es un metodo de esta misma clase ListActivity
        //setListAdapter(dataArrayAdapter);
        /*
        * Recuerda que…
        La clase ListActivity provee métodos para facilitar el uso de las ListViews.
        Una ListView debe tener el id @android:id/list para que tu ListActivity la pueda usar.
        Puedes implementar el método onListItemClick() para detectar cuando un elemento de tu lista recibe un click del usuario(touch)
        Usas el método setListAdapter() para asignarle su adaptador a una lista.
        * */
    }
}
