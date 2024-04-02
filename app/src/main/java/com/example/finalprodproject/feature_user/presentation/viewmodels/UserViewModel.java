package com.example.finalprodproject.feature_user.presentation.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.finalprodproject.feature_main.data.ThemeDTO;
import com.example.finalprodproject.feature_user.data.models.UserDTO;
import com.example.finalprodproject.feature_user.data.models.UserProfile;
import com.example.finalprodproject.feature_user.domain.helpers.UserStorageHandler;
import com.example.finalprodproject.feature_user.domain.repository.UserRepository;
import com.example.finalprodproject.utils.enums.LoaderState;

import java.io.File;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel extends AndroidViewModel {
    private final MutableLiveData<LoaderState> loaderRegister = new MutableLiveData<>(null);
    private final MutableLiveData<LoaderState> loaderLogin = new MutableLiveData<>(null);
    private final MutableLiveData<LoaderState> loaderCheckAuth = new MutableLiveData<>(LoaderState.LOADING);
    private final MutableLiveData<Integer> statusCode = new MutableLiveData<>(0);
    private final UserRepository userRepository;
    private final UserStorageHandler storageHandler;
    private final MutableLiveData<Boolean> isAuth = new MutableLiveData<>(false);
    private final MutableLiveData<UserProfile> profile = new MutableLiveData<>();
    private final MutableLiveData<LoaderState> isUpdateProfile = new MutableLiveData<>(null);


    public UserViewModel(@NonNull Application application, UserStorageHandler storageHandler, UserRepository userRepository) {
        super(application);

        this.storageHandler = storageHandler;
        this.userRepository = userRepository;
    }

    public void register(String phone, String login, String password) {
        loaderRegister.setValue(LoaderState.LOADING);
        statusCode.setValue(0);
        userRepository.register(phone, login, password).enqueue(new Callback<UserDTO>() {
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

    public void login(String phone, String password) {
        loaderLogin.setValue(LoaderState.LOADING);
        statusCode.setValue(0);
        userRepository.login(phone, password).enqueue(new Callback<UserDTO>() {
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

    public MutableLiveData<LoaderState> getLoaderCheckAuth() {
        return loaderCheckAuth;
    }

    public void updateRegisterLoader(LoaderState loaderState) {
        loaderRegister.setValue(loaderState);
    }

    public void updateLoginLoader(LoaderState loaderState) {
        loaderLogin.setValue(loaderState);
    }

    public void updateCheckAuthLoader(LoaderState loaderState) {
        loaderCheckAuth.setValue(loaderState);
    }

    public MutableLiveData<Integer> getStatusCode() {
        return statusCode;
    }

    public void updateStatusCode(int code) {
        statusCode.setValue(code);
    }

    public LiveData<Boolean> checkAuth() {
        loaderCheckAuth.setValue(LoaderState.LOADING);
        userRepository.checkAuth(storageHandler.getToken()).enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(@NonNull Call<UserDTO> call, @NonNull Response<UserDTO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    storageHandler.setProfileData(response.body().getFirstname(), response.body().getPhone(), response.body().getId());
                    isAuth.setValue(true);
                } else isAuth.setValue(false);

                loaderCheckAuth.setValue(LoaderState.SUCCESS);
            }

            @Override
            public void onFailure(@NonNull Call<UserDTO> call, @NonNull Throwable t) {
                Log.e("error_auth", t.getMessage(), t);
                isAuth.setValue(false);
                loaderCheckAuth.setValue(LoaderState.ERROR);
            }
        });

        return isAuth;
    }

    public LiveData<UserProfile> getProfile() {
        userRepository.checkAuth(storageHandler.getToken()).enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(@NonNull Call<UserDTO> call, @NonNull Response<UserDTO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    UserProfile userProfile = new UserProfile();
                    userProfile.setPhone(response.body().getPhone());
                    userProfile.setPassword(response.body().getPassword());
                    userProfile.setFirstname(response.body().getFirstname());
                    userProfile.setLastname(response.body().getFirstname());
                    userProfile.setSurname(response.body().getFirstname());
                    userProfile.setId(response.body().getId());
                    userProfile.setImage(response.body().getImage());
                    userProfile.setCompleteThemeIds(response.body().getCompleteThemeIds());
                    userProfile.setThemeIds(response.body().getThemeIds());

                    userRepository.getUsersThemes(storageHandler.getToken()).enqueue(new Callback<List<ThemeDTO>>() {
                        @Override
                        public void onResponse(@NonNull Call<List<ThemeDTO>> call, @NonNull Response<List<ThemeDTO>> response2) {
                            if (response2.isSuccessful() && response2.body() != null) {
                                userProfile.setThemes(response2.body());
                                profile.setValue(userProfile);
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<List<ThemeDTO>> call, @NonNull Throwable t) {
                            Log.e("error_themes", t.getMessage(), t);
                        }
                    });
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserDTO> call, @NonNull Throwable t) {
                Log.e("error_auth", t.getMessage(), t);
            }
        });

        return profile;
    }

    public LiveData<LoaderState> updateProfile(String firstname, String surname, String lastname) {
        isUpdateProfile.setValue(LoaderState.LOADING);
        userRepository.updateProfile(storageHandler.getToken(), firstname, surname, lastname).enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(@NonNull Call<UserProfile> call, @NonNull Response<UserProfile> response) {
                if (response.isSuccessful() && response.body() != null) {
                    profile.setValue(response.body());
                    isUpdateProfile.setValue(LoaderState.SUCCESS);
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserProfile> call, @NonNull Throwable t) {
                isUpdateProfile.setValue(LoaderState.ERROR);
                Log.e("err_profile_update", t.getMessage(), t);
            }
        });


        return isUpdateProfile;
    }

    public void savePhoto(File file) {
        userRepository.uploadImage(storageHandler.getToken(), file).enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(@NonNull Call<UserDTO> call, @NonNull Response<UserDTO> response) {
                if (response.isSuccessful() && response.body() != null) {

                }
            }

            @Override
            public void onFailure(@NonNull Call<UserDTO> call, @NonNull Throwable t) {
                Log.e("err_user", t.getMessage(), t);
            }
        });
    }
}