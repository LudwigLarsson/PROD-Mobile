package com.example.finalprodproject.feature_roadmap.data.api;

import com.example.finalprodproject.feature_main.data.ThemeDTO;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ThemesAPI {
    @GET("/themes/getById")
    Call<ThemeDTO> getThemeById(@Header("Authorization") String token, @Query("id") String id);

    // не существует
    @GET("/themes/setMark")
    Call<Response> setMark(@Header("Authorization") String token, @Query("id") String id, @Query("mark") int mark);

}
