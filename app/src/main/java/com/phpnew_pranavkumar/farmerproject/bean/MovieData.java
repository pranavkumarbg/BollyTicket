/*
 * Copyright (C) 2015 Pranavkumar Gorawar
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.phpnew_pranavkumar.farmerproject.bean;

import android.os.Parcel;
import android.os.Parcelable;

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
