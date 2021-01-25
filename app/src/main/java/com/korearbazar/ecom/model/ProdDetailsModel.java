package com.korearbazar.ecom.model;

public class ProdDetailsModel {



    public ProdDetailsModel(String id, String name, String details, String stock, String photo, String slug, String size, String size_qty, String size_price, String color, String galleries, String showPrice, String setCurrency, String showPreviousPrice, String user) {
        this.id = id;
        this.name = name;
        this.details = details;
        this.stock = stock;
        this.photo = photo;
        this.slug = slug;
        this.size = size;
        this.size_qty = size_qty;
        this.size_price = size_price;
        this.color = color;
        this.galleries = galleries;
        this.showPrice = showPrice;
        this.setCurrency = setCurrency;
        this.showPreviousPrice = showPreviousPrice;
        this.user = user;
    }

    private String id, name, details, stock, photo, slug, size, size_qty, size_price, color, galleries,showPrice,setCurrency,showPreviousPrice,user;

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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSize_qty() {
        return size_qty;
    }

    public void setSize_qty(String size_qty) {
        this.size_qty = size_qty;
    }

    public String getSize_price() {
        return size_price;
    }

    public void setSize_price(String size_price) {
        this.size_price = size_price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getGalleries() {
        return galleries;
    }

    public void setGalleries(String galleries) {
        this.galleries = galleries;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }


}
