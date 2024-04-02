package com.example.finalprodproject.feature_main.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ThemeDTO {
    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("category")
    private String category;

    @SerializedName("description")
    private String description;

    @SerializedName("explored")
    private boolean explored;

    @SerializedName("author")
    private String author;

    @SerializedName("points")
    private int points;

    @SerializedName("image")
    private String image;

    @SerializedName("students")
    private int students;

    @SerializedName("graduates")
    private int graduates;

    @SerializedName("underThemeIds")
    private List<Integer> underThemeIds;

    @SerializedName("mark")
    private int mark;


    public ThemeDTO() {}

    public ThemeDTO(String id, String title, String category, String description, boolean explored, String author, int points, int students, int graduates, List<Integer> underThemeIds, String image) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.description = description;
        this.explored = explored;
        this.author = author;
        this.points = points;
        this.students = students;
        this.graduates = graduates;
        this.underThemeIds = underThemeIds;
        this.image = image;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getAuthor() {
        return author;
    }

    public int getPoints() {
        return points;
    }

    public int getStudents() {
        return students;
    }

    public int getGraduates() {
        return graduates;
    }

    public List<Integer> getUnderThemeIds() {
        return underThemeIds;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
