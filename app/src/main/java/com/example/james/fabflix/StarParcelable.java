package com.example.james.fabflix;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by james on 5/10/2016.
 */
public class StarParcelable implements Parcelable {

    int id = 0;
    String first = "";
    String last = "";
    String dob = "1-1-1";

    public StarParcelable(String first, String last, String dob) {
        this.first = first;
        this.last = last;
        this.dob = dob;
    }

    public List<String> getStarInfo() {
        List<String> data = new ArrayList<>();
        data.add(first);
        data.add(last);
        data.add(dob);
        return data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {this.first, this.last, this.dob});
    }

    public static final Parcelable.Creator<StarParcelable> CREATOR =
            new Parcelable.Creator<StarParcelable>() {
        public StarParcelable createFromParcel(Parcel in) {
            return new StarParcelable(in);
        }

        public StarParcelable[] newArray(int size) {
            return new StarParcelable[size];
        }
    };

    public StarParcelable(Parcel in) {
        String[] data = new String[3];
        in.readStringArray(data);
        this.first = data[0];
        this.last = data[1];
        this.dob = data[2];
    }
}
