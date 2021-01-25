package com.korearbazar.ecom.model;

import java.io.Serializable;

public class CateModel implements Serializable {

    private String id;
    private String name;
    private String slug;
    private String status;
    private String photo;
    private String is_featured;
    private String image;
    private String subs;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getIs_featured() {
        return is_featured;
    }

    public void setIs_featured(String is_featured) {
        this.is_featured = is_featured;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSubs() {
        return subs;
    }

    public void setSubs(String subs) {
        this.subs = subs;
    }
}
