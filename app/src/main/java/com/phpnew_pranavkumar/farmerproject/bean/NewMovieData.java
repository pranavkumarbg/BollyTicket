package com.phpnew_pranavkumar.farmerproject.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kehooo on 11/19/2015.
 */
public class NewMovieData implements Parcelable {
    public String moviethumbnail;
    public String movieurl1;
    public String movieurl2;
    public String moviename;


    public NewMovieData(Parcel source) {
        moviethumbnail = source.readString();
        movieurl1 = source.readString();
        movieurl2 = source.readString();
        moviename = source.readString();
    }

    public NewMovieData(String moviethumbnail, String movieurl1,String movieurl2, String moviename) {
        this.moviethumbnail = moviethumbnail;
        this.movieurl1 = movieurl1;
        this.movieurl2 = movieurl2;
        this.moviename = moviename;
    }

    public int describeContents() {
        return this.hashCode();
    }


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(moviethumbnail);
        dest.writeString(movieurl1);
        dest.writeString(movieurl2);
        dest.writeString(moviename);
    }

    public static final Parcelable.Creator CREATOR
            = new Parcelable.Creator() {
        public NewMovieData createFromParcel(Parcel in) {
            return new NewMovieData(in);
        }

        public NewMovieData[] newArray(int size) {
            return new NewMovieData[size];
        }
    };
}
