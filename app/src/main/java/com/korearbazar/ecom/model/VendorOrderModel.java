package com.korearbazar.ecom.model;

public class VendorOrderModel {
    private String id;
    private String order_number;
    private String qty;
    private String price;
    private String status;

    public VendorOrderModel(String id, String order_number, String qty, String price, String status) {
        this.id = id;
        this.order_number = order_number;
        this.qty = qty;
        this.price = price;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
