package com.jenking.spandroid.models.base;

public class CommodityModel {
    private String id;

    private String plate_id;

    private String plate_name;

    public String commodity_name;

    private String commodity_price;

    public String commodity_img;

    private String commodity_old_price;

    public String commodity_describe;

    private String state;

    private String commodity_provider;

    private String commodity_provider_username;

    private String commodity_produce_time;

    private String commodity_validity;

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

    public String getCommodity_name() {
        return commodity_name;
    }

    public void setCommodity_name(String commodity_name) {
        this.commodity_name = commodity_name;
    }

    public String getCommodity_price() {
        return commodity_price;
    }

    public void setCommodity_price(String commodity_price) {
        this.commodity_price = commodity_price;
    }

    public String getCommodity_img() {
        return commodity_img;
    }

    public void setCommodity_img(String commodity_img) {
        this.commodity_img = commodity_img;
    }

    public String getCommodity_old_price() {
        return commodity_old_price;
    }

    public void setCommodity_old_price(String commodity_old_price) {
        this.commodity_old_price = commodity_old_price;
    }

    public String getCommodity_describe() {
        return commodity_describe;
    }

    public void setCommodity_describe(String commodity_describe) {
        this.commodity_describe = commodity_describe;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCommodity_provider() {
        return commodity_provider;
    }

    public void setCommodity_provider(String commodity_provider) {
        this.commodity_provider = commodity_provider;
    }

    public String getCommodity_provider_username() {
        return commodity_provider_username;
    }

    public void setCommodity_provider_username(String commodity_provider_username) {
        this.commodity_provider_username = commodity_provider_username;
    }

    public String getCommodity_produce_time() {
        return commodity_produce_time;
    }

    public void setCommodity_produce_time(String commodity_produce_time) {
        this.commodity_produce_time = commodity_produce_time;
    }

    public String getCommodity_validity() {
        return commodity_validity;
    }

    public void setCommodity_validity(String commodity_validity) {
        this.commodity_validity = commodity_validity;
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
        return "CommodityModel{" +
                "id='" + id + '\'' +
                ", plate_id='" + plate_id + '\'' +
                ", plate_name='" + plate_name + '\'' +
                ", commodity_name='" + commodity_name + '\'' +
                ", commodity_price='" + commodity_price + '\'' +
                ", commodity_img='" + commodity_img + '\'' +
                ", commodity_old_price='" + commodity_old_price + '\'' +
                ", commodity_describe='" + commodity_describe + '\'' +
                ", state='" + state + '\'' +
                ", commodity_provider='" + commodity_provider + '\'' +
                ", commodity_provider_username='" + commodity_provider_username + '\'' +
                ", commodity_produce_time='" + commodity_produce_time + '\'' +
                ", commodity_validity='" + commodity_validity + '\'' +
                ", create_time='" + create_time + '\'' +
                ", remark='" + remark + '\'' +
                ", del='" + del + '\'' +
                '}';
    }
}
