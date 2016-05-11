package com.example.james.fabflix;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class StarActivity extends AppCompatActivity {
    private static List<String> star;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star);
        Bundle data = getIntent().getExtras();

        StarParcelable sp = data.getParcelable("starInfo");
        star = sp.getStarInfo();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.starContainer, new StarFragment())
                    .commit();
        }
    }

    public static class StarFragment extends Fragment {
        private static final String LOG_TAG = StarFragment.class.getSimpleName();
        private MovieListArrayAdapter mStarAdapter;
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.starpagefragment, container, false);

            ViewGroup header = (ViewGroup) inflater.inflate(R.layout.star_header, (ViewGroup) rootView);

            TextView starName = (TextView) header.findViewById(R.id.starNameHeader);
            TextView starDob = (TextView) header.findViewById(R.id.starDobHeader);
            String firstNlast = star.get(0) + " " + star.get(1);
            starName.setText(firstNlast);
            starDob.setText(star.get(2));

            List<Movie> movieList = new ArrayList<>();
            movieList.add(new Movie("Obama: The Reckoning", "2017"));
            movieList.add(new Movie("Zoolander 3", "2017"));
            movieList.add(new Movie("Frito Lay", "2001"));
            movieList.add(new Movie("blue cup", "2014"));
            movieList.add(new Movie("green mouse", "2017"));



            mStarAdapter = new MovieListArrayAdapter(getContext(), movieList);

            ListView listView = (ListView) rootView.findViewById(R.id.listViewStar);
            listView.setAdapter(mStarAdapter);
            return rootView;
        }
    }

}
