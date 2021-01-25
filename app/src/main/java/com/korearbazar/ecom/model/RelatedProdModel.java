package com.korearbazar.ecom.model;

import java.io.Serializable;

public class RelatedProdModel implements Serializable {

    String name;
    String slug;
    String thumbnail;
    String showPrice;
    String showPreviousPrice;


    public RelatedProdModel(String name, String slug, String thumbnail, String showPrice, String showPreviousPrice) {
        this.name = name;
        this.slug = slug;
        this.thumbnail = thumbnail;
        this.showPrice = showPrice;
        this.showPreviousPrice = showPreviousPrice;
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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getShowPrice() {
        return showPrice;
    }

    public void setShowPrice(String showPrice) {
        this.showPrice = showPrice;
    }

    public String getShowPreviousPrice() {
        return showPreviousPrice;
    }

    public void setShowPreviousPrice(String showPreviousPrice) {
        this.showPreviousPrice = showPreviousPrice;
    }
}
