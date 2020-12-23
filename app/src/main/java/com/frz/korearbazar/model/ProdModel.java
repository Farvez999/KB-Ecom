package com.frz.korearbazar.model;

import java.io.Serializable;

public class ProdModel implements Serializable {

    private String id;
    private String user_id;
    private String name;
    private String slug;
    private String features;
    private String colors;
    private String thumbnail;
    private String price;
    private String previous_price;
    private String attributes;
    private String size;
    private String size_price;
    private String discount_date;
    private String showPrice;
    private String setCurrency;
    private String showPreviousPrice;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
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

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
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

    public String getShowPrice() {
        return showPrice;
    }

    public void setShowPrice(String showPrice) {
        this.showPrice = showPrice;
    }

    public String getSetCurrency() {
        return setCurrency;
    }

    public void setSetCurrency(String setCurrency) {
        this.setCurrency = setCurrency;
    }

    public String getShowPreviousPrice() {
        return showPreviousPrice;
    }

    public void setShowPreviousPrice(String showPreviousPrice) {
        this.showPreviousPrice = showPreviousPrice;
    }
}
