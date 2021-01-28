package com.example.a2_commoditybrowsing_x.entity;

public class Food {
    private String name;
    private int imageId;
    private String detail;

    public Food(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public Food(String name, int imageId, String detial){
        this.name = name;
        this.imageId = imageId;
        this.detail = detial;
    }

    public String getName(){
        return name;
    }

    public int getImageId(){
        return imageId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
