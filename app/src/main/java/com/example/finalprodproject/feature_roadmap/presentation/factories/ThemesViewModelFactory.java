package com.example.finalprodproject.feature_roadmap.presentation.factories;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.finalprodproject.feature_roadmap.domain.ThemesRepository;
import com.example.finalprodproject.feature_roadmap.presentation.viewmodels.ThemesViewModel;
import com.example.finalprodproject.feature_user.domain.helpers.UserStorageHandler;

public class ThemesViewModelFactory implements ViewModelProvider.Factory {
    private final Application application;
    private final ThemesRepository themeRepository;
    private final UserStorageHandler storageHandler;

    public ThemesViewModelFactory(Application application) {
        this.application = application;
        this.storageHandler = new UserStorageHandler(application);
        this.themeRepository = new ThemesRepository();
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ThemesViewModel.class)) return (T) new ThemesViewModel(application, storageHandler, themeRepository);
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
