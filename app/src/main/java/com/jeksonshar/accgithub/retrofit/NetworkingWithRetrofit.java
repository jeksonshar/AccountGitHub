package com.jeksonshar.accgithub.retrofit;

import com.jeksonshar.accgithub.AccGitHuber;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkingWithRetrofit  {

    public static AccGitHuber makeRequest(String user) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubApi gitHubApi = retrofit.create(GitHubApi.class);
        Call<AccGitHuber> getUserData = gitHubApi.getUserData(user);

        try {
            return getUserData.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
