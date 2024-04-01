package com.example.finalprodproject.feature_user.domain.repository;

import com.example.finalprodproject.feature_main.data.ThemeDTO;
import com.example.finalprodproject.feature_user.data.api.RetrofitBuilder;
import com.example.finalprodproject.feature_user.data.api.UserAPI;
import com.example.finalprodproject.feature_user.data.api.UserApiService;
import com.example.finalprodproject.feature_user.data.models.UserDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class UserRepository {
    public UserAPI userAPI;

    public UserRepository() {
        userAPI = new UserApiService(new RetrofitBuilder()).getUserAPI();
    }

    public Call<UserDTO> register(String email, String login, String password) {
        return userAPI.register(new UserDTO(email, login, password));
    }

    public Call<UserDTO> login(String login, String password) {
        return userAPI.login(new UserDTO(login, password));
    }

    public Call<UserDTO> checkAuth(String token) {
        return userAPI.checkAuth("Bearer " + token);
    }

    public Call<List<ThemeDTO>> getUsersThemes(String token) {
        return userAPI.getUsersThemes("Bearer " + token);
    }

    public Call<List<ThemeDTO>> getUsersCompletedThemes(String token) {
        return userAPI.getUsersCompleteThemes("Bearer " + token);
    }
}
