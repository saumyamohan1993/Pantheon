package com.pantheon.android.model;

public class Region {
    private String name;
    private String type;
    private int thumbnail;

    public Region() {
    }

    public Region(String name, int thumbnail, String type) {
        this.name = name;
        this.type = type;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
