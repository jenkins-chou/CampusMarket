package com.jenking.spandroid.models.base;

public class PlateModel {
    private String id;
    private String plate_name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlate_name() {
        return plate_name;
    }

    public void setPlate_name(String plate_name) {
        this.plate_name = plate_name;
    }

    @Override
    public String toString() {
        return "PlateModel{" +
                "id=" + id +
                ", plate_name='" + plate_name + '\'' +
                '}';
    }
}
