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

        viewHolder viewHolder ;

        //esta es la forma de ligar los datos que tenemos con la layout creada daily_list_item
        //en convertView se guarda la view a utilizar

        //validacion si ya tenemos la vista seteada no hay que crearla nuevamente
        if(convertView == null){
            convertView = LayoutInflater.from(contexto).inflate(R.layout.daily_list_item, parent,false);

            viewHolder = new viewHolder();

            //Si la vista es nula se acude a view holder
            viewHolder.dayTitle = convertView.findViewById(R.id.titleDailyTextView);
            viewHolder.descriptionListTextView = convertView.findViewById(R.id.descriptionListTextView);
            viewHolder.probabilityListTextView = convertView.findViewById(R.id.probabilityListTextView);

            convertView.setTag(viewHolder);

        }else{

            viewHolder = (DailyWeatherAdapter.viewHolder) convertView.getTag();
        }

        //Asignamos los valores de la vista guardada
        //TextView dayTitle = convertView.findViewById(R.id.titleDailyTextView);
        //TextView descriptionListTextView = convertView.findViewById(R.id.descriptionListTextView);
        //TextView probabilityListTextView = convertView.findViewById(R.id.probabilityListTextView);

        //para cada position de los dias que estan guardados en el arraylist
        //se toma cada uno de los atributos

        viewHolder.dayTitle.setText(daysArray.get(position).getDayName());
        viewHolder.descriptionListTextView.setText(daysArray.get(position).getDescription());
        viewHolder.probabilityListTextView.setText(daysArray.get(position).getProbability());


        return convertView;
    }

    //holder de los elementos a cambiar en la UI
    static class viewHolder {

        TextView dayTitle;
        TextView descriptionListTextView;
        TextView probabilityListTextView;
    }
}
