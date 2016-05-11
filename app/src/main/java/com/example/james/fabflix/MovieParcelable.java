package com.example.james.fabflix;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by james on 5/10/2016.
 */
public class MovieParcelable implements Parcelable {

    int id = 0;
    String title = "";
    int year = 0;
    String director = "";
    String banner = "";
    List<Movie> movieList;

    public MovieParcelable(List<Movie> movies) {
        this.movieList = movies;
    }

//    public MovieParcelable(String title, String year, String director, String banner) {
//        this.title = title;
//        this.year = Integer.parseInt(year);
//        this.director = director;
//        this.banner = banner;
//    }

    public List<Movie> getMovieList() {
        return movieList;
    }

//    public List<String> getMovieInfo() {
//        List<String> data = new ArrayList<>();
//        data.add(title);
//        data.add(String.valueOf(year));
//        data.add(director);
//        data.add(banner);
//        return data;
//    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //dest.writeStringArray(new String[] {this.title, String.valueOf(year), this.director, this.banner});
        dest.writeList(movieList);
    }

    public static final Parcelable.Creator<MovieParcelable> CREATOR =
            new Parcelable.Creator<MovieParcelable>() {
                public MovieParcelable createFromParcel(Parcel in) {
                    return new MovieParcelable(in);
                }

                public MovieParcelable[] newArray(int size) {
                    return new MovieParcelable[size];
                }
            };

    public MovieParcelable(Parcel in) {
        movieList = new ArrayList<>();
        in.readList(movieList, null);
//        String[] data = new String[4];
//        in.readStringArray(data);
//        this.title = data[0];
//        this.year = Integer.parseInt(data[1]);
//        this.director = data[2];
//        this.banner = data[3];
    }
}
