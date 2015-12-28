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
