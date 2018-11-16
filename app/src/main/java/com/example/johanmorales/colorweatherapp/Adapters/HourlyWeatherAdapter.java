package com.example.johanmorales.colorweatherapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.johanmorales.colorweatherapp.Hour;
import com.example.johanmorales.colorweatherapp.R;

import java.util.ArrayList;

public class HourlyWeatherAdapter extends BaseAdapter {

    ArrayList<Hour> hoursArray;
    Context contexto;

    public HourlyWeatherAdapter(ArrayList<Hour> hoursArray, Context contexto){
        this.hoursArray = hoursArray;
        this.contexto = contexto;
    }

    @Override
    public int getCount() {
        return hoursArray.size();
    }

    @Override
    public Object getItem(int position) {
        return hoursArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        hourlyViewHolder hourlyViewHolder;

        if(convertView == null){
            convertView = LayoutInflater.from(contexto).inflate(R.layout.hourly_list_item, parent,false);

            hourlyViewHolder = new hourlyViewHolder();

            //Si la vista es nula se acude a view holder
            hourlyViewHolder.title = convertView.findViewById(R.id.titleHourlyItemTextView);
            hourlyViewHolder.description = convertView.findViewById(R.id.descriptionHourlyTextView);

            convertView.setTag(hourlyViewHolder);

        }else{

            hourlyViewHolder = (HourlyWeatherAdapter.hourlyViewHolder) convertView.getTag();
        }

        hourlyViewHolder.title.setText(hoursArray.get(position).getTitle());
        hourlyViewHolder.description.setText(hoursArray.get(position).getDescription());

        return convertView;
    }

    //holder pattern
    static class hourlyViewHolder{

        TextView title;
        TextView description;
    }
}
