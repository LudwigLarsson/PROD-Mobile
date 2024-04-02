package com.example.finalprodproject.feature_user.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserDTO {
    @SerializedName("firstname")
    private String firstname;

    @SerializedName("surname")
    private String surname;

    @SerializedName("lastname")
    private String lastname;

    @SerializedName("phone")
    private String phone;

    @SerializedName("password")
    private String password;

    @SerializedName("id")
    private int id;

    @SerializedName("role")
    private String role;

    @SerializedName("admin")
    private boolean admin;

    @SerializedName("themeIds")
    private List<Integer> themeIds;

    @SerializedName("completeThemeIds")
    private List<Integer> completeThemeIds;

    @SerializedName("token")
    private String token;

    @SerializedName("points")
    private int points;

    @SerializedName("image")
    private String image;

    @SerializedName("achievement")
    private List<Achievement> achievement;

    public UserDTO() {}

    public UserDTO(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }

    public UserDTO(String phone, String firstname, String password) {
        this.phone = phone;
        this.firstname = firstname;
        this.password = password;
        this.surname = null;
        this.lastname = null;
        this.admin = false;
    }

    public UserDTO(String firstname, String phone, String password, int id, String token) {
        this.firstname = firstname;
        this.phone = phone;
        this.password = password;
        this.id = id;
        this.token = token;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public List<Integer> getCompleteThemeIds() {
        return completeThemeIds;
    }

    public List<Integer> getThemeIds() {
        return themeIds;
    }


    public void setCompleteThemeIds(List<Integer> completeThemeIds) {
        this.completeThemeIds = completeThemeIds;
    }

    public void setThemeIds(List<Integer> themeIds) {
        this.themeIds = themeIds;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getSurname() {
        return surname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Achievement> getAchievement() {
        return achievement;
    }

    public void setAchievement(List<Achievement> achievement) {
        this.achievement = achievement;
    }
}
