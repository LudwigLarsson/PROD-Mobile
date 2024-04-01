package com.example.finalprodproject.feature_roadmap.domain;

import com.example.finalprodproject.feature_main.data.ThemeDTO;
import com.example.finalprodproject.feature_roadmap.data.api.ThemesAPI;
import com.example.finalprodproject.feature_roadmap.data.api.ThemesApiService;
import com.example.finalprodproject.feature_shop.data.models.Category;
import com.example.finalprodproject.feature_user.data.api.RetrofitBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class ThemesRepository {
    public ThemesAPI themesAPI;

    public ThemesRepository() {
        themesAPI = new ThemesApiService(new RetrofitBuilder()).getThemeAPI();
    }

    public Call<ThemeDTO> getThemeByID(String token, String id) {
        return themesAPI.getThemeById(token, id);
    }

    public Call<Response> setMark(String token, String id, int mark) {
        return themesAPI.setMark(token, id, mark);
    }

    public Call<List<Category>> getCategories(String token) {
        return themesAPI.getCategories(token);
    }
}
