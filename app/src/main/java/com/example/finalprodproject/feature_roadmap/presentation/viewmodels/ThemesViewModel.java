package com.example.finalprodproject.feature_roadmap.presentation.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.finalprodproject.feature_main.data.ThemeDTO;
import com.example.finalprodproject.feature_roadmap.domain.ThemesRepository;
import com.example.finalprodproject.feature_shop.data.models.Category;
import com.example.finalprodproject.feature_user.domain.helpers.UserStorageHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThemesViewModel extends AndroidViewModel {
    private final ThemesRepository themesRepository;
    private UserStorageHandler userStorageHandler;
    private MutableLiveData<ThemeDTO> themeData = new MutableLiveData<>();
    private MutableLiveData<List<String>> categoryList = new MutableLiveData<>(new ArrayList<>());

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

    public LiveData<List<String>> getCategories() {
        themesRepository.getCategories(userStorageHandler.getToken()).enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(@NonNull Call<List<Category>> call, @NonNull Response<List<Category>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<String> result = response.body().stream().map(Category::getCategory).collect(Collectors.toList());
                    categoryList.setValue(result);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Category>> call, @NonNull Throwable t) {
                Log.e("error_themes", t.getMessage(), t);
            }
        });

        return categoryList;
    }
}
