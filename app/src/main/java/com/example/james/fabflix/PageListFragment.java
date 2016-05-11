package com.example.james.fabflix;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.TextView;

import java.util.List;

public class PageListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.search_movie_list, container, false);
        Bundle args = getArguments();
        MovieParcelable sp = args.getParcelable("movieList");
        try {
            List<Movie> movies = sp.getMovieList();

            ViewGroup list1 = (ViewGroup) rootView.findViewById(R.id.cvList1);
            ViewGroup list2 = (ViewGroup) rootView.findViewById(R.id.cvList2);
            ViewGroup list3 = (ViewGroup) rootView.findViewById(R.id.cvList3);



            TextView tvList1 = (TextView) list1.findViewById(R.id.movieCardViewTitle);
            TextView tvList2 = (TextView) list2.findViewById(R.id.movieCardViewTitle);
            TextView tvList3 = (TextView) list3.findViewById(R.id.movieCardViewTitle);

            switch(movies.size()) {
                case 3:
                    tvList1.setText(movies.get(0).getTitle());
                    tvList2.setText(movies.get(1).getTitle());
                    tvList3.setText(movies.get(2).getTitle());

                    list1.setOnClickListener(new CardClickListener(getActivity(), movies.get(0)));
                    list2.setOnClickListener(new CardClickListener(getActivity(), movies.get(1)));
                    list3.setOnClickListener(new CardClickListener(getActivity(), movies.get(2)));
                    break;
                case 2:
                    tvList1.setText(movies.get(0).getTitle());
                    tvList2.setText(movies.get(1).getTitle());

                    list1.setOnClickListener(new CardClickListener(getActivity(), movies.get(0)));
                    list2.setOnClickListener(new CardClickListener(getActivity(), movies.get(1)));

                    ((ViewManager)list3.getParent()).removeView(list3); //removes the cardview because the there is no more items in the list
                    break;
                case 1:
                    tvList1.setText(movies.get(0).getTitle());

                    list1.setOnClickListener(new CardClickListener(getActivity(), movies.get(0)));

                    ((ViewManager)list2.getParent()).removeView(list2);
                    ((ViewManager)list3.getParent()).removeView(list3);
                    break;
                case 0:
                    ((ViewManager)list1.getParent()).removeView(list1);
                    ((ViewManager)list2.getParent()).removeView(list2);
                    ((ViewManager)list3.getParent()).removeView(list3);
                    break;
            }



        }catch (NullPointerException e) {
            //ERROR: Parcelable returned NOTHING
            e.printStackTrace();
        }
        return rootView;
    }
}