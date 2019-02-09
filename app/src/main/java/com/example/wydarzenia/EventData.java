package com.example.wydarzenia;

public class EventData {
    private String name;
    private String brief;
    private String content;
    private int id;
    private int image;

    public EventData(String name, String brief, String content, int id, int image) {
        this.name = name;
        this.brief = brief;
        this.content = content;
        this.id = id;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getBrief() {
        return brief;
    }

    public String getContent() {
        return content;
    }

    public int getId() {
        return id;
    }

    public int getImage() {
        return image;
    }
}
