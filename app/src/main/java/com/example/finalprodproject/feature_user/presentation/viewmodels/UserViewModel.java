package com.example.finalprodproject.feature_user.presentation.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.finalprodproject.feature_user.data.models.UserDTO;
import com.example.finalprodproject.feature_user.domain.helpers.UserStorageHandler;
import com.example.finalprodproject.feature_user.domain.repository.UserRepository;
import com.example.finalprodproject.utils.enums.LoaderState;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel extends AndroidViewModel {
    private final MutableLiveData<LoaderState> loaderRegister = new MutableLiveData<>(null);
    private final MutableLiveData<LoaderState> loaderLogin = new MutableLiveData<>(null);
    private final MutableLiveData<Integer> statusCode = new MutableLiveData<>(0);
    private final UserRepository userRepository;
    private final UserStorageHandler storageHandler;
    private final MutableLiveData<Boolean> isAuth = new MutableLiveData<>(false);

    public UserViewModel(@NonNull Application application, UserStorageHandler storageHandler, UserRepository userRepository) {
        super(application);

        this.storageHandler = storageHandler;
        this.userRepository = userRepository;
    }

    public void register(String email, String login, String password) {
        loaderRegister.setValue(LoaderState.LOADING);
        statusCode.setValue(0);
        userRepository.register(email, login, password).enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(@NonNull Call<UserDTO> call, @NonNull Response<UserDTO> response) {
                statusCode.setValue(response.code());
                if (response.isSuccessful() && response.body() != null) loaderRegister.setValue(LoaderState.SUCCESS);
            }

            @Override
            public void onFailure(@NonNull Call<UserDTO> call, @NonNull Throwable t) {
                Log.e("error_reg", t.getMessage(), t);
                loaderRegister.setValue(LoaderState.ERROR);
            }
        });
    }

    public void login(String login, String password) {
        loaderLogin.setValue(LoaderState.LOADING);
        statusCode.setValue(0);
        userRepository.login(login, password).enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(@NonNull Call<UserDTO> call, @NonNull Response<UserDTO> response) {
                statusCode.setValue(response.code());
                if (response.isSuccessful() && response.body() != null) {
                    storageHandler.setToken(response.body().getToken());
                    loaderLogin.setValue(LoaderState.SUCCESS);
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserDTO> call, @NonNull Throwable t) {
                Log.e("error_login", t.getMessage(), t);
                loaderLogin.setValue(LoaderState.ERROR);
            }
        });
    }

    public LiveData<LoaderState> getRegisterLoader() { return loaderRegister; }

    public MutableLiveData<LoaderState> getLoginLoader() {
        return loaderLogin;
    }

    public void updateRegisterLoader(LoaderState loaderState) {
        loaderRegister.setValue(loaderState);
    }

    public void updateLoginLoader(LoaderState loaderState) {
        loaderLogin.setValue(loaderState);
    }

    public MutableLiveData<Integer> getStatusCode() {
        return statusCode;
    }

    public void updateStatusCode(int code) {
        statusCode.setValue(code);
    }

    public LiveData<Boolean> checkAuth() {
        userRepository.checkAuth(storageHandler.getToken()).enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(@NonNull Call<UserDTO> call, @NonNull Response<UserDTO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    storageHandler.setProfileData(response.body().getLogin(), response.body().getEmail(), response.body().getId());
                    isAuth.setValue(true);
                } else isAuth.setValue(false);
            }

            @Override
            public void onFailure(@NonNull Call<UserDTO> call, @NonNull Throwable t) {
                Log.e("error_auth", t.getMessage(), t);
                isAuth.setValue(false);
            }
        });

        return isAuth;
    }
}
