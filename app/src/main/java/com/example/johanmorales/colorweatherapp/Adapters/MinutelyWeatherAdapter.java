package com.example.johanmorales.colorweatherapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.johanmorales.colorweatherapp.Minute;
import com.example.johanmorales.colorweatherapp.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MinutelyWeatherAdapter extends RecyclerView.Adapter {

    private Context contexto;
    private ArrayList<Minute> minuteArrayList;

    public MinutelyWeatherAdapter(ArrayList<Minute> minuteArrayList, Context contexto){
        this.contexto = contexto;
        this.minuteArrayList = minuteArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(contexto).inflate(R.layout.minutely_list_item, viewGroup, false);

        MinuteViewHolder minutelyViewHolder = new MinuteViewHolder(view);

        return minutelyViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        ((MinuteViewHolder)viewHolder).onBind(position);
    }

    @Override
    public int getItemCount() {

        if(minuteArrayList == null)
            return 0;

        return minuteArrayList.size();
    }

    class MinuteViewHolder extends RecyclerView.ViewHolder{

        //add the elements of the layout
        TextView titleTextView, rainProbabilityTextView;

        public MinuteViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.titleMinuteTextView);
            rainProbabilityTextView = itemView.findViewById(R.id.rainProbabilityTextView);
        }

        public void onBind(int position){

            Minute minute = minuteArrayList.get(position);

            titleTextView.setText(minute.getTitle());
            rainProbabilityTextView.setText(minute.getRainProbability());

        }
    }
}
