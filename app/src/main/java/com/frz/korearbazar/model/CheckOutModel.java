package com.frz.korearbazar.model;

public class CheckOutModel {
        private int id;
        private String title;
        private String price;
        private String quantity;
        private String image;

    public CheckOutModel() {
    }

    public CheckOutModel(String title, String price, String quantity, String image) {
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
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

        public String getImage() {
        return image;
    }

        public void setImage(String image) {
        this.image = image;
    }


}


