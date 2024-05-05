package com.example.finalproject.model;

import java.util.Date;

public class EventModel {
    private String key = "";
    private String name = "";
    private Date date;
    private double latitude;
    private double longitude;

    public EventModel(String name, Date date, double latitude, double longitude) {
        this.name = name;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
