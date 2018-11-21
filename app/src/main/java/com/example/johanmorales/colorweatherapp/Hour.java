package com.example.johanmorales.colorweatherapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Hour implements Parcelable {

    private String title, description;

    protected Hour(Parcel in) {
        title = in.readString();
        description = in.readString();
    }

    //sin el constructor la clase que implementa parcelable no funciona
    //asi no tenga nada dentro

    public Hour() {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //se pasan los atributos que se van a escribir en este caso son solo 2 string
        dest.writeString(title);
        dest.writeString(description);
    }

    @Override
    public int describeContents() {
        return 0;
    } //esto no se usa realmente

    public static final Creator<Hour> CREATOR = new Creator<Hour>() {
        @Override
        public Hour createFromParcel(Parcel in) {
            return new Hour(in);
        }

        @Override
        public Hour[] newArray(int size) {
            return new Hour[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
