package com.example.finalprodproject.feature_user.data.api;

import com.example.finalprodproject.feature_main.data.ThemeDTO;
import com.example.finalprodproject.feature_user.data.models.UserDTO;
import com.example.finalprodproject.feature_user.data.models.UserProfile;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public interface UserAPI {
    @POST("auth/register")
    Call<UserDTO> register(@Body UserDTO userDTO);

    @POST("auth/sign-in")
    Call<UserDTO> login(@Body UserDTO userDTO);

    @GET("me/profile")
    Call<UserDTO> checkAuth(@Header("Authorization") String token);

    @GET("themes/me")
    Call<List<ThemeDTO>> getUsersThemes(@Header("Authorization") String token);

    @GET("themes/complete")
    Call<List<ThemeDTO>> getUsersCompleteThemes(@Header("Authorization") String token);

    @PATCH("me/setProfile")
    Call<UserProfile> profileUpdate(@Header("Authorization") String token, @Body UserDTO userProfile);
}
