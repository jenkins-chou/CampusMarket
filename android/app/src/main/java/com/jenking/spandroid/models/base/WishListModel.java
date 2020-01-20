package com.jenking.spandroid.models.base;

public class WishListModel {
    private String id;

    private String plate_id;

    private String plate_name;

    private String wish_name;

    private String wish_price;

    private String wish_describe;

    private String wish_provider;

    private String wish_provider_username;

    private String wish_provider_avatar;

    private String create_time;

    private String remark;

    private String del;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlate_id() {
        return plate_id;
    }

    public void setPlate_id(String plate_id) {
        this.plate_id = plate_id;
    }

    public String getPlate_name() {
        return plate_name;
    }

    public void setPlate_name(String plate_name) {
        this.plate_name = plate_name;
    }

    public String getWish_name() {
        return wish_name;
    }

    public void setWish_name(String wish_name) {
        this.wish_name = wish_name;
    }

    public String getWish_price() {
        return wish_price;
    }

    public void setWish_price(String wish_price) {
        this.wish_price = wish_price;
    }

    public String getWish_describe() {
        return wish_describe;
    }

    public void setWish_describe(String wish_describe) {
        this.wish_describe = wish_describe;
    }

    public String getWish_provider() {
        return wish_provider;
    }

    public void setWish_provider(String wish_provider) {
        this.wish_provider = wish_provider;
    }

    public String getWish_provider_username() {
        return wish_provider_username;
    }

    public void setWish_provider_username(String wish_provider_username) {
        this.wish_provider_username = wish_provider_username;
    }

    public String getWish_provider_avatar() {
        return wish_provider_avatar;
    }

    public void setWish_provider_avatar(String wish_provider_avatar) {
        this.wish_provider_avatar = wish_provider_avatar;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }

    @Override
    public String toString() {
        return "WishListModel{" +
                "id='" + id + '\'' +
                ", plate_id='" + plate_id + '\'' +
                ", plate_name='" + plate_name + '\'' +
                ", wish_name='" + wish_name + '\'' +
                ", wish_price='" + wish_price + '\'' +
                ", wish_describe='" + wish_describe + '\'' +
                ", wish_provider='" + wish_provider + '\'' +
                ", wish_provider_username='" + wish_provider_username + '\'' +
                ", wish_provider_avatar='" + wish_provider_avatar + '\'' +
                ", create_time='" + create_time + '\'' +
                ", remark='" + remark + '\'' +
                ", del='" + del + '\'' +
                '}';
    }
}
