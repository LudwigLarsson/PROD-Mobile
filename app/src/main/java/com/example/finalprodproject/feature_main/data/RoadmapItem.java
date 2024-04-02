package com.example.finalprodproject.feature_main.data;

public class RoadmapItem {
    private String text;
    private String image;
    private int scores;
    private boolean isLoad = false;
    private int id;

    public RoadmapItem() {}

    public RoadmapItem(String text, String image, int scores, int id) {
        this.text = text;
        this.image = image;
        this.scores = scores;
        this.id = id;
    }

    public int getScores() {
        return scores;
    }

    public String getImage() {
        return image;
    }

    public String getText() {
        return text;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setScores(int scores) {
        this.scores = scores;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getIsLoad() {
        return isLoad;
    }

    public void setIsLoad(boolean isLoad) {
        this.isLoad = isLoad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
