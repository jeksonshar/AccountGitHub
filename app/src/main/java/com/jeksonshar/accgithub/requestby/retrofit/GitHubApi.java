package com.jeksonshar.accgithub.requestby.retrofit;

import com.jeksonshar.accgithub.model.AccGitHuber;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubApi {

    @GET("/users/{user}")
    Call<AccGitHuber> getUserData(@Path("user") String user);
}
