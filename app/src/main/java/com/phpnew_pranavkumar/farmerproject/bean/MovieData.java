package com.phpnew_pranavkumar.farmerproject.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kehooo on 11/19/2015.
 */
public class MovieData implements Parcelable {
    public String moviethumbnail;
    public String movieurl;
    public String moviename;



//    public String getMovieurl() {
//        return movieurl;
//    }
//
//    public void setMovieurl(String movieurl) {
//        this.movieurl = movieurl;
//    }
//
//
//
//    public String getMoviethumbnail() {
//        return moviethumbnail;
//    }
//
//    public void setMoviethumbnail(String moviethumbnail) {
//        this.moviethumbnail = moviethumbnail;
//    }
//
//
//
//    public String getMoviename() {
//        return moviename;
//    }
//
//    public void setMoviename(String moviename) {
//        this.moviename = moviename;
//    }
//

    public MovieData(Parcel source) {
        moviethumbnail = source.readString();
        movieurl = source.readString();
        moviename = source.readString();
    }

    public MovieData(String moviethumbnail, String movieurl, String moviename) {
        this.moviethumbnail = moviethumbnail;
        this.movieurl = movieurl;
        this.moviename = moviename;
    }

    public int describeContents() {
        return this.hashCode();
    }


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(moviethumbnail);
        dest.writeString(movieurl);
        dest.writeString(moviename);
    }

    public static final Parcelable.Creator CREATOR
            = new Parcelable.Creator() {
        public MovieData createFromParcel(Parcel in) {
            return new MovieData(in);
        }

        public MovieData[] newArray(int size) {
            return new MovieData[size];
        }
    };
}
