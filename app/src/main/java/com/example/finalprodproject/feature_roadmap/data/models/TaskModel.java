package com.example.finalprodproject.feature_roadmap.data.models;

import com.google.gson.annotations.SerializedName;

public class TaskModel {
    @SerializedName("id")
    private int id;

    @SerializedName("description")
    private String description;

    @SerializedName("response")
    private String response;

    @SerializedName("explored")
    private Boolean explored;

    @SerializedName("started")
    private Boolean started;

    @SerializedName("image")
    private Boolean image;

    public TaskModel() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Boolean getExplored() {
        return explored;
    }

    public void setExplored(Boolean explored) {
        this.explored = explored;
    }

    public Boolean getStarted() {
        return started;
    }

    public void setStarted(Boolean started) {
        this.started = started;
    }

    public Boolean getImage() {
        return image;
    }

    public void setImage(Boolean image) {
        this.image = image;
    }
}
