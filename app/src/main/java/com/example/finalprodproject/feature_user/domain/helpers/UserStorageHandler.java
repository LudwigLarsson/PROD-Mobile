package com.example.finalprodproject.feature_user.domain.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;


import java.io.IOException;
import java.security.GeneralSecurityException;

public class UserStorageHandler {
    private EncryptedSharedPreferences encryptedSharedPreferences;

    public UserStorageHandler(Context ctx) {
        try {
            MasterKey masterKey = new MasterKey.Builder(ctx, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .build();
            encryptedSharedPreferences = (EncryptedSharedPreferences) EncryptedSharedPreferences.create(ctx, "user", masterKey,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV, EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);
        } catch (IOException | GeneralSecurityException err) {
            Log.e("auth error", err.getMessage(), err);
        }
    }

    public void setToken(String token) {
        SharedPreferences.Editor editor = encryptedSharedPreferences.edit();

        editor.putString("token", token);

        editor.apply();
    }

    public void setProfileData(String firstname, String phone, int userID) {
        SharedPreferences.Editor editor = encryptedSharedPreferences.edit();

        editor.putString("firstname", firstname);
        editor.putString("phone", phone);
        editor.putInt("id", userID);

        editor.apply();
    }

    public void logout() {
        SharedPreferences.Editor editor = encryptedSharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public int getUserID() {
        return encryptedSharedPreferences.getInt("id", 0);
    }

    public String getToken() {
        return encryptedSharedPreferences.getString("token", "");
    }
}