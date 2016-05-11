package com.example.james.fabflix;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SearchResultActivity extends FragmentActivity {
    private static final int LIST_COUNT = 3;
    List<Movie> movies;

    SearchCollectionAdapter mSearchCollectionAdapter;
    ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        movies = new ArrayList<>();
        //TODO: GET REAL DATA
        movies.add(new Movie("Zoolander 1", 2001, "Ben Stiller", "http://www.hobbyconsolas.com/sites/default/files/reviews/136922/zoolander_1_banner.jpg"));
        movies.add(new Movie("Zoolander 2", 2001, "Ben Stiller", "http://www.hobbyconsolas.com/sites/default/files/reviews/136922/zoolander_1_banner.jpg"));
        movies.add(new Movie("Zoolander 3", 2001, "Ben Stiller", "http://www.hobbyconsolas.com/sites/default/files/reviews/136922/zoolander_1_banner.jpg"));
        movies.add(new Movie("Zoolander 4", 2001, "Ben Stiller", "http://www.hobbyconsolas.com/sites/default/files/reviews/136922/zoolander_1_banner.jpg"));
        movies.add(new Movie("Zoolander 5", 2001, "Ben Stiller", "http://www.hobbyconsolas.com/sites/default/files/reviews/136922/zoolander_1_banner.jpg"));
        movies.add(new Movie("Zoolander 6", 2001, "Ben Stiller", "http://www.hobbyconsolas.com/sites/default/files/reviews/136922/zoolander_1_banner.jpg"));
        movies.add(new Movie("Zoolander 7", 2001, "Ben Stiller", "http://www.hobbyconsolas.com/sites/default/files/reviews/136922/zoolander_1_banner.jpg"));
        movies.add(new Movie("Zoolander 8", 2001, "Ben Stiller", "http://www.hobbyconsolas.com/sites/default/files/reviews/136922/zoolander_1_banner.jpg"));
        movies.add(new Movie("Zoolander 9", 2001, "Ben Stiller", "http://www.hobbyconsolas.com/sites/default/files/reviews/136922/zoolander_1_banner.jpg"));
        movies.add(new Movie("Zoolander 10", 2001, "Ben Stiller", "http://www.hobbyconsolas.com/sites/default/files/reviews/136922/zoolander_1_banner.jpg"));
        movies.add(new Movie("Zoolander 11", 2001, "Ben Stiller", "http://www.hobbyconsolas.com/sites/default/files/reviews/136922/zoolander_1_banner.jpg"));
        movies.add(new Movie("Zoolander 12", 2001, "Ben Stiller", "http://www.hobbyconsolas.com/sites/default/files/reviews/136922/zoolander_1_banner.jpg"));



        mSearchCollectionAdapter = new SearchCollectionAdapter(
                getSupportFragmentManager(), movies);
        mViewPager = (ViewPager) findViewById(R.id.pager);

        //TODO: find total size of movie list here!
        final int totalSize = (int) Math.ceil(movies.size()/LIST_COUNT);

        final TextView tvSwipePosition = (TextView) findViewById(R.id.swipePosition);
        final ImageView leftArrow = (ImageView) findViewById(R.id.leftArrow);
        final ImageView rightArrow = (ImageView) findViewById(R.id.rightArrow);

        String swipePosition = "1/" + String.valueOf(totalSize);
        tvSwipePosition.setText(swipePosition);
        //cant go left anyways..
        leftArrow.setImageResource(R.drawable.ic_chevron_left_black_disabled_36dp);

        //if there is only one page set right arrow to disabled
        if (totalSize == 1)
            rightArrow.setImageResource(R.drawable.ic_chevron_right_black_disabled_36dp);

        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                String swipePosition = String.valueOf(position+1) + "/" + String.valueOf(totalSize);
                tvSwipePosition.setText(swipePosition);

                if (position == 0)
                    leftArrow.setImageResource(R.drawable.ic_chevron_left_black_disabled_36dp);
                else
                    leftArrow.setImageResource(R.drawable.ic_chevron_left_black_36dp);

                if (position >= totalSize-1)
                    rightArrow.setImageResource(R.drawable.ic_chevron_right_black_disabled_36dp);
                else
                    rightArrow.setImageResource(R.drawable.ic_chevron_right_black_36dp);

            }
        });

        mViewPager.setAdapter(mSearchCollectionAdapter);

    }

    public static class SearchCollectionAdapter extends FragmentStatePagerAdapter {



        private List<List<Movie>> fragmentMovieList;
        private List<Movie> movieList;
        public SearchCollectionAdapter(FragmentManager fm, List<Movie> movies) {
            super(fm);
            this.movieList = movies;
            int listSize = (int) Math.ceil(movieList.size()/LIST_COUNT);
            fragmentMovieList = new ArrayList<>();
            for (int i = 0; i < movieList.size();) {
                List<Movie> tmpList = new ArrayList<>();
                for (int j = 0; j < LIST_COUNT; j++) {
                    tmpList.add(movieList.get(i++));
                }
                fragmentMovieList.add(tmpList);
            }
        }

        @Override
        public Fragment getItem(int i) {
            Fragment fragment = new PageListFragment();

            //insert Objects to send to fragment
            Bundle args = new Bundle();
            args.putInt("size", fragmentMovieList.size());
            List<Movie> m = fragmentMovieList.get(i);
            MovieParcelable mp = new MovieParcelable(m);
            args.putParcelable("movieList", mp);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getCount() {
            return (int) Math.ceil(movieList.size()/3);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Object " + (position + 1);
        }
    }


}
