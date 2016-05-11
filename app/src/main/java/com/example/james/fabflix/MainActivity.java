package com.example.james.fabflix;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.SearchView;

public class MainActivity extends AppCompatActivity {
    private SearchView sv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sv = (SearchView) findViewById(R.id.searchView);
        String svText = sv.getQuery().toString();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //TODO: send to pagination search page if finds list, else 404 page
                Intent intent = new Intent(getApplicationContext(), SearchResultActivity.class);
                startActivity(intent);
//                if (movie found) {
//                    go to search list
//                }
//                else {
//                    NothingFoundDialogFragment nf = new NothingFoundDialogFragment();
//                    nf.show(getSupportFragmentManager(), "Nothing Found");
//                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    public static class NothingFoundDialogFragment extends DialogFragment {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Search Returned Empty")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            return builder.create();
        }
    }
}
