package com.example.finalprodproject.feature_roadmap.data.models;

import com.example.finalprodproject.feature_shop.data.models.Category;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryListDTO {
    private List<Category> categories;

    public CategoryListDTO() {}

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

}
