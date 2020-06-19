package com.example.githubapi.Rest;

import com.example.githubapi.model.GitHubUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubUserEndPoints {


    @GET("search/users?q=language:java+location:nairobi")
    Call<GitHubUser> getUsers();
    @GET("search/{name}")
    Call<GitHubUser> getName(@Path("name") String name);
    @GET("getCurrentLocation/{coordinate}")
    Call<GitHubUser> getCurrentLocation(@Path("coordinate") String coordinate);
}
