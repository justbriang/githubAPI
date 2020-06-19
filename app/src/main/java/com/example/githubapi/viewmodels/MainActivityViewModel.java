package com.example.githubapi.viewmodels;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.githubapi.model.GitHubUser;
import com.example.githubapi.repositories.GithubUsersRepo;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    private GithubUsersRepo githubUsersRepo;

    private Context context;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
        githubUsersRepo = githubUsersRepo.getInstance(context);


    }

    public MutableLiveData<List<GitHubUser>> getTasks() {

        return githubUsersRepo.getUsers();
    }
}
