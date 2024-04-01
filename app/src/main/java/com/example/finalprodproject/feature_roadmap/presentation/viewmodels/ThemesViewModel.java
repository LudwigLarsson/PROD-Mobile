package com.example.finalprodproject.feature_roadmap.presentation.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.finalprodproject.feature_main.data.ThemeDTO;
import com.example.finalprodproject.feature_roadmap.domain.ThemesRepository;
import com.example.finalprodproject.feature_user.domain.helpers.UserStorageHandler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThemesViewModel extends AndroidViewModel {
    private final ThemesRepository themesRepository;
    private UserStorageHandler userStorageHandler;
    private MutableLiveData<ThemeDTO> themeData = new MutableLiveData<>();

    public ThemesViewModel(@NonNull Application application, UserStorageHandler storageHandler, ThemesRepository themesRepository) {
        super(application);

        this.themesRepository = themesRepository;
        this.userStorageHandler = storageHandler;
    }


    public LiveData<ThemeDTO> loadThemeData(String id) {
        themesRepository.getThemeByID(userStorageHandler.getToken(), id).enqueue(new Callback<ThemeDTO>() {
            @Override
            public void onResponse(@NonNull Call<ThemeDTO> call, @NonNull Response<ThemeDTO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    themeData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ThemeDTO> call, @NonNull Throwable t) {
                Log.e("error_themes", t.getMessage(), t);
            }
        });

        return themeData;
    }

    public void setMark(int mark) {

    }
}
