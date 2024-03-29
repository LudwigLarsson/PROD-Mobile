package com.example.finalprodproject.feature_user.data.api.dto;

import com.example.finalprodproject.feature_user.data.models.UserDTO;
import com.google.gson.annotations.SerializedName;

public class RegisterUserDTO {
    @SerializedName("profile")
    private UserDTO userDTO;

    public RegisterUserDTO() {};

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
