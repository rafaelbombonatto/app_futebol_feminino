package com.example.soccernews.domain;

public class News {
    private String title;
    private String description;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public News(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
