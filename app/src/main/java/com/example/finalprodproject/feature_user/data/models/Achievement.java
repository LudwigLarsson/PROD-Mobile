package com.example.finalprodproject.feature_user.data.models;

import com.google.gson.annotations.SerializedName;

public class Achievement {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("isCompleted")
    private boolean isCompleted;

    @SerializedName("image")
    private String image;

    public Achievement() {}

    public Achievement(int id, String name, boolean isCompleted, String image) {
        this.id = id;
        this.name = name;
        this.isCompleted = isCompleted;
        this.image = image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
