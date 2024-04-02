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
import com.example.finalprodproject.feature_shop.data.models.CourseShopModel;
import com.example.finalprodproject.feature_user.domain.helpers.UserStorageHandler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThemesViewModel extends AndroidViewModel {
    private final ThemesRepository themesRepository;
    private UserStorageHandler userStorageHandler;
    private MutableLiveData<ThemeDTO> themeData = new MutableLiveData<>();
    private MutableLiveData<Set<String>> categoryList = new MutableLiveData<>(new HashSet<>());
    private MutableLiveData<List<CourseShopModel>> courses = new MutableLiveData<>(new ArrayList<>());
    private MutableLiveData<Boolean> isBuyCourse = new MutableLiveData<>(false);

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

    public LiveData<Set<String>> getCategories() {
        themesRepository.getCategories(userStorageHandler.getToken()).enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(@NonNull Call<List<Category>> call, @NonNull Response<List<Category>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Set<String> result = response.body().stream().map(Category::getCategory).collect(Collectors.toCollection(HashSet::new));
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

    public LiveData<List<CourseShopModel>> getCourses() {
        themesRepository.getProducts(userStorageHandler.getToken()).enqueue(new Callback<List<CourseShopModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<CourseShopModel>> call, @NonNull Response<List<CourseShopModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    courses.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<CourseShopModel>> call, @NonNull Throwable t) {
                Log.e("err_courses", t.getMessage(), t);
            }
        });

        return courses;
    }

    public MutableLiveData<Boolean> buyCourse(int shopID) {
        themesRepository.buyProduct(userStorageHandler.getToken(), shopID).enqueue(new Callback<CourseShopModel>() {
            @Override
            public void onResponse(@NonNull Call<CourseShopModel> call, @NonNull Response<CourseShopModel> response) {
                if (response.isSuccessful() && response.body() != null) isBuyCourse.setValue(response.body().isBought());
            }

            @Override
            public void onFailure(@NonNull Call<CourseShopModel> call, @NonNull Throwable t) {
                Log.e("err_buy_course", t.getMessage(), t);
            }
        });

        return isBuyCourse;
    }

    public List<CourseShopModel> getCoursesList(String category) {
        if (courses.getValue() == null) return new ArrayList<>();
        List<CourseShopModel> result = courses.getValue();
        return result.stream().filter(course -> course.getCategory().equals(category)).collect(Collectors.toList());
    }

    public LiveData<Boolean> getIsBuyCourse() {
        return isBuyCourse;
    }
}
