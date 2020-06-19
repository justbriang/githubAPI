package com.example.githubapi.factories;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.githubapi.viewmodels.MainActivityViewModel;

public class MainActivityFactory extends ViewModelProvider.AndroidViewModelFactory {
    private Application application;

    public MainActivityFactory(@NonNull Application application, Application application1) {
        super(application);
        this.application = application1;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return(T)new MainActivityViewModel(application);
    }
}
