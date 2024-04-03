package com.example.finalprodproject.feature_roadmap.presentation.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.finalprodproject.feature_main.data.ThemeDTO;
import com.example.finalprodproject.feature_roadmap.data.models.TaskModel;
import com.example.finalprodproject.feature_roadmap.data.models.UnderTheme;
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
    private final UserStorageHandler userStorageHandler;
    private final MutableLiveData<ThemeDTO> themeData = new MutableLiveData<>();
    private final MutableLiveData<Set<String>> categoryList = new MutableLiveData<>(new HashSet<>());
    private final MutableLiveData<List<CourseShopModel>> courses = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<Boolean> isBuyCourse = new MutableLiveData<>(false);
    private final MutableLiveData<List<UnderTheme>> underThemes = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<Double> percent = new MutableLiveData<>(0.0);
    private final MutableLiveData<Boolean> isMarkSaved = new MutableLiveData<>(false);
    private final MutableLiveData<UnderTheme> underTheme = new MutableLiveData<>();
    private final MutableLiveData<List<TaskModel>> tasksList = new MutableLiveData<>();

    public ThemesViewModel(@NonNull Application application, UserStorageHandler storageHandler, ThemesRepository themesRepository) {
        super(application);

        this.themesRepository = themesRepository;
        this.userStorageHandler = storageHandler;
    }


    public LiveData<ThemeDTO> loadThemeData(int id) {
        themesRepository.getThemeByID(userStorageHandler.getToken(), id).enqueue(new Callback<ThemeDTO>() {
            @Override
            public void onResponse(@NonNull Call<ThemeDTO> call, @NonNull Response<ThemeDTO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    themeData.setValue(response.body());
                    List<UnderTheme> underThemeList = response.body().getUnderThemes();
                    underThemes.setValue(underThemeList);

                    double count = 0;
                    for (UnderTheme underTheme : underThemeList) {
                        if (underTheme.isExplored()) count++;
                    }

                    percent.setValue(count / underThemeList.size());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ThemeDTO> call, @NonNull Throwable t) {
                Log.e("error_themes", t.getMessage(), t);
            }
        });

        return themeData;
    }

    public LiveData<Boolean> setMark(int mark, int underTheme) {
        isMarkSaved.setValue(false);
        themesRepository.setMark(userStorageHandler.getToken(), underTheme, mark).enqueue(new Callback<UnderTheme>() {
            @Override
            public void onResponse(@NonNull Call<UnderTheme> call, @NonNull Response<UnderTheme> response) {
                if (response.isSuccessful() && response.body() != null) isMarkSaved.setValue(true);
            }

            @Override
            public void onFailure(@NonNull Call<UnderTheme> call, @NonNull Throwable t) {
                Log.e("err_mark", t.getMessage(), t);
            }
        });

        return isMarkSaved;
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

        if (category.equals("Все")) return result;

        return result.stream().filter(course -> course.getCategory().equals(category)).collect(Collectors.toList());
    }

    public LiveData<UnderTheme> getByUnderTheme(int underThemeID) {
        themesRepository.getByUnderTheme(userStorageHandler.getToken(), underThemeID).enqueue(new Callback<UnderTheme>() {
            @Override
            public void onResponse(@NonNull Call<UnderTheme> call, @NonNull Response<UnderTheme> response) {
                if (response.isSuccessful() && response.body() != null) {
                    underTheme.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<UnderTheme> call, @NonNull Throwable t) {
                Log.e("err_buy_course", t.getMessage(), t);
            }
        });

        return underTheme;
    }

    public LiveData<Boolean> getIsBuyCourse() {
        return isBuyCourse;
    }

    public LiveData<List<UnderTheme>> getUnderThemes() {
        return underThemes;
    }

    public LiveData<Double> getPercent() {
        return percent;
    }

    public LiveData<List<TaskModel>> getTasksList(int underThemeID) {
        themesRepository.getTasksList(userStorageHandler.getToken(), underThemeID).enqueue(new Callback<List<TaskModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<TaskModel>> call, @NonNull Response<List<TaskModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    tasksList.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<TaskModel>> call, @NonNull Throwable t) {
                Log.e("err_tasks", t.getMessage(), t);
            }
        });

        return tasksList;
    }
}
