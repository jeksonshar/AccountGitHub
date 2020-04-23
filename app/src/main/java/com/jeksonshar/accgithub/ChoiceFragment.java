package com.jeksonshar.accgithub;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

public class ChoiceFragment extends Fragment {
    static final String USER_LOGIN = "jeksonshar";

    private Button okHttp;
    private Button retrofit;

    public ChoiceFragment() {
        super(R.layout.fragment_choice);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);

        new InternetRequestTask().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choice, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        okHttp = view.findViewById(R.id.ok_http);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        okHttp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getParentFragmentManager().
                        beginTransaction().
                        replace(R.id.fragment_container, AccGitHubFragment.newInstance(USER_LOGIN));
                transaction = transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    // The class for a background task
    private class InternetRequestTask extends AsyncTask<Void, Void, AccGitHuber> {

        // Method will be called in background thread
        @Override
        protected AccGitHuber doInBackground(Void... voids) {
            return executeRequest();
        }

        // Method will be called in main thread after the doInBackground() has finished
        @Override
        protected void onPostExecute(AccGitHuber gitHuber) {
            //TODO
        }
    }

    private AccGitHuber executeRequest() {
        return NetworkingWithOkHttp.makeRequest(USER_LOGIN);
    }
}
