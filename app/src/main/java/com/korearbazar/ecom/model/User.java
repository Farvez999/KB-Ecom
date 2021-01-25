package com.korearbazar.ecom.model;

public class User {

    int id;
    String name,photo,zip,city,country,address,phone,email,created_at,updated_at,is_provider,status,verification_link,email_verified, affilate_code,affilate_income,shop_name,owner_name,shop_number,shop_address,reg_number,shop_message,shop_details,shop_image,f_url,g_url,t_url,l_url,is_vendor,f_check,g_check,t_check,l_check,mail_sent,shipping_cost,current_balance,date,ban;

//    public User(int id, String name, String photo, String zip, String city, String country, String address, String phone, String email, String created_at, String updated_at, String is_provider, String status, String verification_link, String email_verified, String affilate_code, String affilate_income, String shop_name, String owner_name, String shop_number, String shop_address, String reg_number, String shop_message, String shop_details, String shop_image, String f_url, String g_url, String t_url, String l_url, String is_vendor, String f_check, String g_check, String t_check, String l_check, String mail_sent, String shipping_cost, String current_balance, String date, String ban) {
//    }

    public User(int id, String name, String photo, String zip, String city, String country, String address, String string) {
        this.id = id;
        this.name = name;
        this.photo = photo;
        this.zip = zip;
        this.city = city;
        this.country = country;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.is_provider = is_provider;
        this.status = status;
        this.verification_link = verification_link;
        this.email_verified = email_verified;
        this.affilate_code = affilate_code;
        this.affilate_income = affilate_income;
        this.shop_name = shop_name;
        this.owner_name = owner_name;
        this.shop_number = shop_number;
        this.shop_address = shop_address;
        this.reg_number = reg_number;
        this.shop_message = shop_message;
        this.shop_details = shop_details;
        this.shop_image = shop_image;
        this.f_url = f_url;
        this.g_url = g_url;
        this.t_url = t_url;
        this.l_url = l_url;
        this.is_vendor = is_vendor;
        this.f_check = f_check;
        this.g_check = g_check;
        this.t_check = t_check;
        this.l_check = l_check;
        this.mail_sent = mail_sent;
        this.shipping_cost = shipping_cost;
        this.current_balance = current_balance;
        this.date = date;
        this.ban = ban;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getIs_provider() {
        return is_provider;
    }

    public void setIs_provider(String is_provider) {
        this.is_provider = is_provider;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVerification_link() {
        return verification_link;
    }

    public void setVerification_link(String verification_link) {
        this.verification_link = verification_link;
    }

    public String getEmail_verified() {
        return email_verified;
    }

    public void setEmail_verified(String email_verified) {
        this.email_verified = email_verified;
    }

    public String getAffilate_code() {
        return affilate_code;
    }

    public void setAffilate_code(String affilate_code) {
        this.affilate_code = affilate_code;
    }

    public String getAffilate_income() {
        return affilate_income;
    }

    public void setAffilate_income(String affilate_income) {
        this.affilate_income = affilate_income;
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

    public String getShop_number() {
        return shop_number;
    }

    public void setShop_number(String shop_number) {
        this.shop_number = shop_number;
    }

    public String getShop_address() {
        return shop_address;
    }

    public void setShop_address(String shop_address) {
        this.shop_address = shop_address;
    }

    public String getReg_number() {
        return reg_number;
    }

    public void setReg_number(String reg_number) {
        this.reg_number = reg_number;
    }

    public String getShop_message() {
        return shop_message;
    }

    public void setShop_message(String shop_message) {
        this.shop_message = shop_message;
    }

    public String getShop_details() {
        return shop_details;
    }

    public void setShop_details(String shop_details) {
        this.shop_details = shop_details;
    }

    public String getShop_image() {
        return shop_image;
    }

    public void setShop_image(String shop_image) {
        this.shop_image = shop_image;
    }

    public String getF_url() {
        return f_url;
    }

    public void setF_url(String f_url) {
        this.f_url = f_url;
    }

    public String getG_url() {
        return g_url;
    }

    public void setG_url(String g_url) {
        this.g_url = g_url;
    }

    public String getT_url() {
        return t_url;
    }

    public void setT_url(String t_url) {
        this.t_url = t_url;
    }

    public String getL_url() {
        return l_url;
    }

    public void setL_url(String l_url) {
        this.l_url = l_url;
    }

    public String getIs_vendor() {
        return is_vendor;
    }

    public void setIs_vendor(String is_vendor) {
        this.is_vendor = is_vendor;
    }

    public String getF_check() {
        return f_check;
    }

    public void setF_check(String f_check) {
        this.f_check = f_check;
    }

    public String getG_check() {
        return g_check;
    }

    public void setG_check(String g_check) {
        this.g_check = g_check;
    }

    public String getT_check() {
        return t_check;
    }

    public void setT_check(String t_check) {
        this.t_check = t_check;
    }

    public String getL_check() {
        return l_check;
    }

    public void setL_check(String l_check) {
        this.l_check = l_check;
    }

    public String getMail_sent() {
        return mail_sent;
    }

    public void setMail_sent(String mail_sent) {
        this.mail_sent = mail_sent;
    }

    public String getShipping_cost() {
        return shipping_cost;
    }

    public void setShipping_cost(String shipping_cost) {
        this.shipping_cost = shipping_cost;
    }

    public String getCurrent_balance() {
        return current_balance;
    }

    public void setCurrent_balance(String current_balance) {
        this.current_balance = current_balance;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBan() {
        return ban;
    }

    public void setBan(String ban) {
        this.ban = ban;
    }
}
