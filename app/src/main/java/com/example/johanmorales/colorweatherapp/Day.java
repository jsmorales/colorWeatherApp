package com.example.johanmorales.colorweatherapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by PERSONAL on 13/11/2018.
 */

/*Esta clase extiende desde parcelable para poder viajar de una actividad
a otra sin que se destruya.
* */
public class Day implements Parcelable {

    private String dayName;
    private String description;
    private String probability;

    protected Day(Parcel in) {
        dayName = in.readString();
        description = in.readString();
        probability = in.readString();
    }

    public static final Creator<Day> CREATOR = new Creator<Day>() {
        @Override
        public Day createFromParcel(Parcel in) {
            return new Day(in);
        }

        @Override
        public Day[] newArray(int size) {
            return new Day[size];
        }
    };

    public Day() {

    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProbability() {
        return probability;
    }

    public void setProbability(String probability) {
        this.probability = probability;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(dayName);
        dest.writeString(description);
        dest.writeString(probability);
    }
}
