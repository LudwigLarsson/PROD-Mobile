package com.example.finalprodproject.feature_shop.data.models;

import com.google.gson.annotations.SerializedName;

public class CourseShopModel {
    @SerializedName("title")
    private String title;

    @SerializedName("id")
    private int id;

    @SerializedName("price")
    private int price;

    @SerializedName("description")
    private String description;

    @SerializedName("image")
    private String image;

    @SerializedName("isBought")
    private boolean isBought;

    @SerializedName("category")
    private String category;

    public CourseShopModel() {}

    public CourseShopModel(String title, int id, int price, String description, String image, boolean isBought, String category) {
        this.title = title;
        this.id = id;
        this.price = price;
        this.description = description;
        this.image = image;
        this.isBought = isBought;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public boolean isBought() {
        return isBought;
    }

    public void setBought(boolean bought) {
        isBought = bought;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
