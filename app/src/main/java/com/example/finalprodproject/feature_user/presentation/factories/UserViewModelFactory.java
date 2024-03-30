package com.example.finalprodproject.feature_user.presentation.factories;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.finalprodproject.feature_user.domain.helpers.UserStorageHandler;
import com.example.finalprodproject.feature_user.domain.repository.UserRepository;
import com.example.finalprodproject.feature_user.presentation.viewmodels.UserViewModel;

public class UserViewModelFactory implements ViewModelProvider.Factory {
    private final Application application;
    private final UserRepository userRepository;
    private final UserStorageHandler storageHandler;

    public UserViewModelFactory(Application application) {
        this.application = application;
        this.storageHandler = new UserStorageHandler(application);
        this.userRepository = new UserRepository();
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(UserViewModel.class)) return (T) new UserViewModel(application, storageHandler, userRepository);
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
