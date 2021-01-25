package com.korearbazar.ecom.model;

public class FavoriteSellerModel {
    private String id;
    private String shop_name;
    private String owner_name;
    private String shop_address;


    public FavoriteSellerModel(String id, String shop_name, String owner_name, String shop_address) {
        this.id = id;
        this.shop_name = shop_name;
        this.owner_name = owner_name;
        this.shop_address = shop_address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getShop_address() {
        return shop_address;
    }

    public void setShop_address(String shop_address) {
        this.shop_address = shop_address;
    }
}
