package com.example.finalprodproject.feature_user.data.api;

import com.example.finalprodproject.feature_user.data.models.UserDTO;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserAPI {
    @POST("auth/register")
    Call<UserDTO> register(@Body UserDTO userDTO);

    @POST("auth/sign-in")
    Call<UserDTO> login(@Body UserDTO userDTO);

    @GET("me/profile")
    Call<UserDTO> checkAuth(@Header("Authorization") String token);
}
