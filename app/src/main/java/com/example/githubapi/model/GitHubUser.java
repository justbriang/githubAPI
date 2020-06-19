package com.example.githubapi.model;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;

public class GitHubUser {
    @SerializedName("login")
    private String login;
    @SerializedName("avatar_url")
    private String avatar;
    @SerializedName("url")
    private String url;


    public GitHubUser() {
    }

    public GitHubUser(String login, String avatar, String url) {
        this.login = login;
        this.avatar = avatar;
        this.url = url;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
