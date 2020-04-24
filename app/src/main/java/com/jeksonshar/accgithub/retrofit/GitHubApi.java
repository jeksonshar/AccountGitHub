package com.jeksonshar.accgithub.retrofit;

import com.jeksonshar.accgithub.AccGitHuber;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubApi {

    @GET("/users/{user}")
    Call<AccGitHuber> getUserData(@Path("user") String user);
}
