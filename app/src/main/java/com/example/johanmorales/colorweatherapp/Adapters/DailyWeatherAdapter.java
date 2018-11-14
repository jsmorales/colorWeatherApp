package com.example.johanmorales.colorweatherapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.johanmorales.colorweatherapp.Day;
import com.example.johanmorales.colorweatherapp.R;

import java.util.ArrayList;

/**
 * Created by PERSONAL on 13/11/2018.
 */

public class DailyWeatherAdapter extends BaseAdapter {

    //este adaptador tendra un array de dias de la clase Day y un context
    ArrayList<Day> daysArray;
    Context contexto;

    public DailyWeatherAdapter (ArrayList<Day> daysArray, Context contexto){

        this.daysArray = daysArray;
        this.contexto = contexto;
    }

    @Override
    public int getCount() {
        return daysArray.size();
    }

    @Override
    public Object getItem(int position) {
        return daysArray.get(position);
    }

    @Override
    //Este metodo no se utiliza ne esta app
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //esta es la forma de ligar los datos que tenemos con la layout creada daily_list_item
        convertView = LayoutInflater.from(contexto).inflate(R.layout.daily_list_item,null);

        TextView dayTitle = convertView.findViewById(R.id.titleDailyTextView);
        

        return convertView;
    }
}
