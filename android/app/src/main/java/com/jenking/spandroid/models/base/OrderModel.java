package com.jenking.spandroid.models.base;

public class OrderModel {
    private String id;
    private String status;
    private String money;
    public String commodity_name;
    public String commodity_img;
    public String commodity_describe;
    private String shipping_address_detail;

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    private String create_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommodity_name() {
        return commodity_name;
    }

    public void setCommodity_name(String commodity_name) {
        this.commodity_name = commodity_name;
    }

    public String getCommodity_img() {
        return commodity_img;
    }

    public void setCommodity_img(String commodity_img) {
        this.commodity_img = commodity_img;
    }

    public String getCommodity_describe() {
        return commodity_describe;
    }

    public void setCommodity_describe(String commodity_describe) {
        this.commodity_describe = commodity_describe;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getShipping_address_detail() {
        return shipping_address_detail;
    }

    public void setShipping_address_detail(String shipping_address_detail) {
        this.shipping_address_detail = shipping_address_detail;
    }

    @Override
    public String toString() {
        return "OrderModel{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                ", money='" + money + '\'' +
                ", shipping_address_detail='" + shipping_address_detail + '\'' +
                ", commodity_name='" + commodity_name + '\'' +
                ", commodity_img='" + commodity_img + '\'' +
                ", commodity_describe='" + commodity_describe + '\'' +
                '}';
    }
}
