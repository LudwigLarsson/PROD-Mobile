package com.example.finalprodproject.feature_user.data.models;

import com.example.finalprodproject.feature_main.data.ThemeDTO;

import java.util.List;

public class UserProfile extends UserDTO {
    private List<ThemeDTO> themes;
    private List<ThemeDTO> completedThemes;

    public UserProfile() {
        super();
    }

    public UserProfile(String login, String email, String password, int id, String token) {
        super(login, email, password, id, token);
    }

    public void setThemes(List<ThemeDTO> themes) {
        this.themes = themes;
    }

    public List<ThemeDTO> getThemes() {
        return themes;
    }

    public List<ThemeDTO> getCompletedThemes() {
        return completedThemes;
    }

    public void setCompletedThemes(List<ThemeDTO> completedThemes) {
        this.completedThemes = completedThemes;
    }
}
