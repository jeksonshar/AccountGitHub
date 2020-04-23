package com.jeksonshar.accgithub;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkingWithOkHttp {

    public static AccGitHuber makeRequest(String user) {

        OkHttpClient httpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.github.com/users/" + user)
                .build();

        String responseBody = null;

        try {
            Response response = httpClient.newCall(request).execute();
            responseBody = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Type type = new TypeToken<AccGitHuber>() {}.getType();
        AccGitHuber result = new Gson().fromJson(responseBody, type);

        return result;
    }
}
