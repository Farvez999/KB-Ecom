package com.korearbazar.ecom.model;

public class CategoryDetailsProdModel {
    private String id;
    private String name;
    private String slug;
    private String photo;
    private String thumbnail;
    private String price;
    private String previous_price;

    public CategoryDetailsProdModel(String id, String name, String slug, String photo, String thumbnail, String price, String previous_price) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.photo = photo;
        this.thumbnail = thumbnail;
        this.price = price;
        this.previous_price = previous_price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrevious_price() {
        return previous_price;
    }

    public void setPrevious_price(String previous_price) {
        this.previous_price = previous_price;
    }
}
