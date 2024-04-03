package com.example.finalprodproject.feature_roadmap.data.api;

import com.example.finalprodproject.feature_main.data.ThemeDTO;
import com.example.finalprodproject.feature_roadmap.data.models.TaskModel;
import com.example.finalprodproject.feature_roadmap.data.models.UnderTheme;
import com.example.finalprodproject.feature_shop.data.models.Category;
import com.example.finalprodproject.feature_shop.data.models.CourseShopModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ThemesAPI {
    @GET("themes/getBy/{id}")
    Call<ThemeDTO> getThemeById(@Header("Authorization") String token, @Path("id") int id);

    @POST("themes/addGrade/{underThemeId}/{grade}")
    Call<UnderTheme> setMark(@Header("Authorization") String token, @Path("underThemeId") int underThemeId, @Path("grade") int mark);

    @GET("category")
    Call<List<Category>> getCategories(@Header("Authorization") String token);

    @GET("product/")
    Call<List<CourseShopModel>> getProducts(@Header("Authorization") String token);

    @POST("product/buy/{id}")
    Call<CourseShopModel> buyProduct(@Header("Authorization") String token, @Path("id") int productID);

    @GET("themes/getByUnderTheme/{id}")
    Call<UnderTheme> getByUnderTheme(@Header("Authorization") String token, @Path("id") int underThemeID);

    @GET("themes/getAllTasks/{id}")
    Call<List<TaskModel>> getTasksList(@Header("Authorization") String token, @Path("id") int underThemeID);
}
