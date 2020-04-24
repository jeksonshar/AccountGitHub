package com.jeksonshar.accgithub;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jeksonshar.accgithub.okhttp.NetworkingWithOkHttp;
import com.jeksonshar.accgithub.retrofit.NetworkingWithRetrofit;
import com.squareup.picasso.Picasso;

public class AccGitHubFragment extends Fragment {

    static final String USER_LOGIN = "jeksonshar";     // you may enter any user of GitHub

    private AccGitHuber mAccGitHuber;

    private String clicked;
    private ImageView avatarView;
    private TextView userLoginView;
    private TextView userId;
    private Button repositoriesButton;

    public AccGitHubFragment(String clicked) {
        this.clicked = clicked;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new InternetRequestTask().execute();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(
                R.layout.fragment_acc_github,
                container,
                false);

        userLoginView = v.findViewById(R.id.user_login_view);
        userId = v.findViewById(R.id.user_id_view);
        avatarView = v.findViewById(R.id.user_avatar_view);
        repositoriesButton = v.findViewById(R.id.repository_button);

        return  v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

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
            mAccGitHuber = gitHuber;

            userLoginView.setText(mAccGitHuber.getLogin());
            userId.setText( String.valueOf(mAccGitHuber.getId()));
            Picasso.get().load(mAccGitHuber.getAvatarUrl()).into(avatarView);
        }
    }

    private AccGitHuber executeRequest() {
        if (clicked.equals("OkHttp")) {
            return NetworkingWithOkHttp.makeRequest(USER_LOGIN);
        } else {
            return NetworkingWithRetrofit.makeRequest(USER_LOGIN);
        }
    }
}
