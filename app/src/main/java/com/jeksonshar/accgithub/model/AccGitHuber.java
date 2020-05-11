package com.jeksonshar.accgithub.model;

import com.google.gson.annotations.SerializedName;

public class AccGitHuber {
    private long id;
    private String login;

    @SerializedName("avatar_url")
    private String avatarUrl;

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
