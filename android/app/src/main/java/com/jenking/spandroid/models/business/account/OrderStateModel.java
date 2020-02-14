package com.jenking.spandroid.models.business.account;

public class OrderStateModel {
    private String id;
    private String state;
    private String create_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    @Override
    public String toString() {
        return "OrderStateModel{" +
                "id='" + id + '\'' +
                ", state='" + state + '\'' +
                ", create_time='" + create_time + '\'' +
                '}';
    }
}
