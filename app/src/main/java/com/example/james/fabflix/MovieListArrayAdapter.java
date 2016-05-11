package com.example.james.fabflix;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by james on 5/10/2016.
 */
public class MovieListArrayAdapter extends ArrayAdapter<Movie> {

    public MovieListArrayAdapter(Context context, List<Movie> movie) {
        super(context, 0, movie);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie_list_item, parent, false);
        }
        TextView tvTitle = (TextView) convertView.findViewById(R.id.movieTitleItem);
        TextView tvYear = (TextView) convertView.findViewById(R.id.movieYearItem);

        tvTitle.setText(movie.getTitle());
        tvYear.setText(String.valueOf(movie.getYear()));

        return convertView;
    }
}
