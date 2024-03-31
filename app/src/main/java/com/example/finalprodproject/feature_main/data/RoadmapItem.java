package com.example.finalprodproject.feature_main.data;

public class RoadmapItem {
    private String text;
    private String image;
    private int scores;

    public RoadmapItem() {}

    public RoadmapItem(String text, String image, int scores) {
        this.text = text;
        this.image = image;
        this.scores = scores;
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
}
