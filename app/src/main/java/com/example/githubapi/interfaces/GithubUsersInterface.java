package com.example.githubapi.interfaces;

import com.example.githubapi.model.GitHubUser;

import java.util.List;

public interface GithubUsersInterface {
    void getUsers(List<GitHubUser> tasks);
}
