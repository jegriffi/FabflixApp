package com.example.james.fabflix;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MoviePageActivity extends AppCompatActivity {
    public Movie movieData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_page);

        Bundle data = getIntent().getExtras();
        movieData = (Movie) data.getSerializable("movieInfo");
        //getIntent().putExtra("movieInfo", )
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.movieContainer, new MoviePageFragment())
                    .commit();
        }
    }

    public static class MoviePageFragment extends Fragment {
        private static final String LOG_TAG = MoviePageFragment.class.getSimpleName();
        private ArrayAdapter<String> mMovieAdapter;

        private List<Star> starTest;
        private Movie movie;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.moviepagefragment, container, false);

            ViewGroup header = (ViewGroup) rootView.findViewById(R.id.movieHeader);
            TextView tvMovieTitle = (TextView) header.findViewById(R.id.movieTitleHeader);
            TextView tvMovieYear = (TextView) header.findViewById(R.id.movieYearHeader);
            TextView tvMovieDirector = (TextView) header.findViewById(R.id.directorHeader);
            TextView tvMovieGenres = (TextView) header.findViewById(R.id.genreHeader);

            Intent intent = getActivity().getIntent();
            Movie movieData = (Movie) intent.getSerializableExtra("movieInfo");
            tvMovieTitle.setText(movieData.getTitle());
            tvMovieYear.setText(String.valueOf(movieData.getYear()));
            tvMovieDirector.setText(movieData.getDirector());
            //String genreList = getGenreStringList(movieData.getGenres());
            //tvMovieGenres.setText(genreList);
            tvMovieGenres.setText("Horror");


            starTest = new ArrayList<>();

            starTest.add(new Star("matt", "damon", "01/01/10001", ""));
            starTest.add(new Star("james", "griffin", "01/25/1991", ""));
            starTest.add(new Star("mirelle", "rangel", "06/09/1997", ""));
            starTest.add(new Star("jonathan", "nguyen", "02/29/1955", ""));
            starTest.add(new Star("james", "reese", "09/09/1009", ""));

            ListView listView = (ListView) rootView.findViewById(R.id.listViewMoviePage);

            mMovieAdapter = new ArrayAdapter<>(getActivity(),
                    R.layout.actor_list_item,
                    R.id.starNameItem,
                    getFirstLastStringNameList(starTest));
            listView.setAdapter(mMovieAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getActivity(), StarActivity.class);

                    Star s = starTest.get(position);
                    StarParcelable data = new StarParcelable(s.getFirst(), s.getLast(), s.getDob());
                    intent.putExtra("starInfo", data);
                    startActivity(intent);
                    //getActivity().finish();
                }
            });

            return rootView;

        }

        public String getGenreStringList(Set<Genre> s) {
            String genres = "";
            for (Genre g : s) {
                genres += g.getGenre() + "  ";
            }
            return genres;
        }

        public List<String> getFirstLastStringNameList(List<Star> stars) {
            List<String> s = new ArrayList<>();
            for(Star tmp : stars) {
                s.add(tmp.getFirst() + " " + tmp.getLast());
            }
            return s;
        }

        public String concatGenres(Set<Genre> data) {
            String genres = "";

            for (Genre g: data) {
                genres += g.getGenre() + "  ";
            }
            return genres;
        }
    }

}
