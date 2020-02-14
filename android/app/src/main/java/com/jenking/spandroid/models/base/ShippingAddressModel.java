package com.jenking.spandroid.models.base;

public class ShippingAddressModel {
    private String id;
    private String user_id;
    private String province;
    private String city;
    private String address;
    private String is_dafault;
    private String create_time;
    private String remark;
    private String del;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIs_dafault() {
        return is_dafault;
    }

    public void setIs_dafault(String is_dafault) {
        this.is_dafault = is_dafault;
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
        return "ShippingAddressModel{" +
                "id='" + id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", is_dafault='" + is_dafault + '\'' +
                ", create_time='" + create_time + '\'' +
                ", remark='" + remark + '\'' +
                ", del='" + del + '\'' +
                '}';
    }
}
