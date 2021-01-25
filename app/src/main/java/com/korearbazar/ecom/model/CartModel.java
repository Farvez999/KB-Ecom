package com.korearbazar.ecom.model;

public class CartModel {
    private int id;
    private String title;
    private String price;
    private String quantity;
    private String size_qty;
    private String image;
    private String stock;
    private String dp;
    private String product_id;

    public CartModel() {
    }

    public CartModel(String title, String price, String quantity, String size_qty, String image, String dp, String stock, String product_id) {
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.size_qty = size_qty;
        this.image = image;
        this.dp = dp;
        this.stock = stock;
        this.product_id = product_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getSize_qty() {
        return size_qty;
    }

    public void setSize_qty(String size_qty) {
        this.size_qty = size_qty;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getDp() {
        return dp;
    }

    public void setDp(String dp) {
        this.dp = dp;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    @Override
    public String toString() {
        return "CartModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price='" + price + '\'' +
                ", quantity='" + quantity + '\'' +
                ", image='" + image + '\'' +
                ", stock='" + stock + '\'' +
                ", dp='" + dp + '\'' +
                ", product_id='" + product_id + '\'' +
                '}';
    }
}
