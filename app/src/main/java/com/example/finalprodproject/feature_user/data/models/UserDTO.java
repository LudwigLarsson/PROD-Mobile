package com.example.finalprodproject.feature_user.data.models;

import com.google.gson.annotations.SerializedName;

public class UserDTO {
    @SerializedName("login")
    private String login;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("id")
    private int id;

    @SerializedName("token")
    private String token;

    public UserDTO() {}

    public UserDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public UserDTO(String email, String login, String password) {
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public UserDTO(String login, String email, String password, int id, String token) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.id = id;
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setId(int id) {
        this.id = id;
    }
}
