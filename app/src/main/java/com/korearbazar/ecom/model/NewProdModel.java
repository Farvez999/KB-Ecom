package com.korearbazar.ecom.model;

import java.io.Serializable;

public class NewProdModel implements Serializable {

    private String name;
    private String slug;
    private String thumbnail;
    private String colors;
    private String price;
    private String previous_price;
    private String size;
    private String size_price;
    private String discount_date;

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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSize_price() {
        return size_price;
    }

    public void setSize_price(String size_price) {
        this.size_price = size_price;
    }

    public String getDiscount_date() {
        return discount_date;
    }

    public void setDiscount_date(String discount_date) {
        this.discount_date = discount_date;
    }
}
