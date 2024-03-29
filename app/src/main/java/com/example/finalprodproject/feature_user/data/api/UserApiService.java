package com.example.finalprodproject.feature_user.data.api;

import com.example.finalprodproject.utils.Constants;

public class UserApiService {
    private final UserAPI userAPI;

    public UserApiService(RetrofitBuilder retrofitService) {
        userAPI = retrofitService.getInstance(Constants.USER_API_PATH).create(UserAPI.class);
    }

    public UserAPI getUserAPI() {
        return userAPI;
    }
}