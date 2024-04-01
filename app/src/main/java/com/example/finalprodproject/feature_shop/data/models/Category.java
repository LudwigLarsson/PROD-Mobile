package com.example.finalprodproject.feature_shop.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Category {
    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("category")
    private String category;

    @SerializedName("description")
    private String description;

    @SerializedName("explored")
    private boolean explored;

    @SerializedName("points")
    private int points;

    @SerializedName("author")
    private String author;

    @SerializedName("students")
    private int students;

    @SerializedName("graduates")
    private int graduates;

    @SerializedName("underThemeIds")
    private List<Integer> underThemeIds;

    public Category() {}

    public Category(int id, String title, String category, String description, boolean explored, int points, String author, int students, int graduates, List<Integer> underThemeIds) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.description = description;
        this.explored = explored;
        this.points = points;
        this.author = author;
        this.students = students;
        this.graduates = graduates;
        this.underThemeIds = underThemeIds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isExplored() {
        return explored;
    }

    public void setExplored(boolean explored) {
        this.explored = explored;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getStudents() {
        return students;
    }

    public void setStudents(int students) {
        this.students = students;
    }

    public List<Integer> getUnderThemeIds() {
        return underThemeIds;
    }

    public void setUnderThemeIds(List<Integer> underThemeIds) {
        this.underThemeIds = underThemeIds;
    }

    public int getGraduates() {
        return graduates;
    }

    public void setGraduates(int graduates) {
        this.graduates = graduates;
    }
}
