package com.jeksonshar.accgithub;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.concurrent.ExecutionException;

public class AccGitHubFragment extends Fragment {

    private static final String KEY_CLICKED = "CLICKED";
    private static final String USER_LOGIN = "jeksonshar";     // you may enter any user of GitHub

    private AccGitHuber mAccGitHuber;
    private AccGitHubViewModel mAccGitHubViewModel;

    private ChoiceFragment.Clicked clicked;
    private ImageView avatarView;
    private TextView userLoginView;
    private TextView userId;
    private Button repositoriesButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        clicked = (ChoiceFragment.Clicked) arguments.getSerializable(KEY_CLICKED);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

    mAccGitHubViewModel = new ViewModelProvider(this,
            new ViewModelFactory(clicked, USER_LOGIN))
            .get(AccGitHubViewModel.class);

        return  inflater.inflate(R.layout.fragment_acc_github, container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        userLoginView = getView().findViewById(R.id.user_login_view);
        userId = getView().findViewById(R.id.user_id_view);
        avatarView = getView().findViewById(R.id.user_avatar_view);
        repositoriesButton = getView().findViewById(R.id.repository_button);

        try {
            mAccGitHubViewModel.getRequest().observe(getViewLifecycleOwner(),
                new Observer<AccGitHuber>() {

                    @Override
                    public void onChanged(AccGitHuber accGitHuber) {
                        mAccGitHuber = accGitHuber;
                        if (mAccGitHuber != null) {
                            userLoginView.setText(mAccGitHuber.getLogin());
                            userId.setText(String.valueOf(mAccGitHuber.getId()));
                            Picasso.get().load(mAccGitHuber.getAvatarUrl()).into(avatarView);
                        } else {
                            Toast.makeText(getContext(), R.string.no_internet, Toast.LENGTH_LONG)
                                .show();
                            }
                        }
                    });
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        repositoriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String urlRepo = "https://github.com/" +
                        USER_LOGIN +
                        "?tab=repositories";
// использование неявного интента для открытия страницы в Android браузере
                Uri addressRepositories = Uri.parse(urlRepo);
                Intent openRepositories = new Intent(Intent.ACTION_VIEW, addressRepositories);
                startActivity(openRepositories);
            }
        });
    }

    static AccGitHubFragment makeInstance(ChoiceFragment.Clicked clicked) {
        Bundle args = new Bundle();
        args.putSerializable(KEY_CLICKED, clicked);

        AccGitHubFragment fragment = new AccGitHubFragment();
        fragment.setArguments(args);
        return  fragment;
    }
}
