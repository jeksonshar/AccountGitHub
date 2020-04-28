package com.jeksonshar.accgithub;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ChoiceFragment extends Fragment {

    private Button okHttp;
    private Button retrofit;

    public ChoiceFragment() {
        super(R.layout.fragment_choice);
    }

    enum Clicked {OK_HTTP, RETROFIT}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        retrofit = view.findViewById(R.id.retrofit);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        okHttp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeTransaction(Clicked.OK_HTTP);
            }
        });

        retrofit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeTransaction(Clicked.RETROFIT);
            }
        });
    }
    private void makeTransaction(Clicked clicked) {
        FragmentTransaction transaction = getParentFragmentManager().
                beginTransaction().
                replace(R.id.fragment_container, AccGitHubFragment.makeInstance(clicked));
        transaction = transaction.addToBackStack(null);
        transaction.commit();
    }
}