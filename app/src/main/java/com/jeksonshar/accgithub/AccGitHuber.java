package com.jeksonshar.accgithub;

import com.google.gson.annotations.SerializedName;

public class AccGitHuber {
    private long id;
    private String login;

    @SerializedName("avatar_url")
    private String avatarUrl;

    public AccGitHuber(String userLogin) {
        this.login = userLogin;
    }

    public long getId() {
        return id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getLogin() {
        return login;
    }
}
