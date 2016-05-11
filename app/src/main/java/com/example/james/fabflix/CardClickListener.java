package com.example.james.fabflix;

import android.content.Context;
import android.content.Intent;
import android.view.View;

/**
 * Created by james on 5/10/2016.
 */
public class CardClickListener implements View.OnClickListener {
    private Movie movie;
    private Context context;

    public CardClickListener(Context context, Movie movie) {
        this.context = context;
        this.movie = movie;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, MoviePageActivity.class);
        intent.putExtra("movieInfo", movie);
        context.startActivity(intent);
    }
}