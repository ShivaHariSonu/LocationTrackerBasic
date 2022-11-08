package com.nsl.locationtracker.model;

import com.google.gson.JsonObject;

public class KafkaObject {
    private Integer id;
    private GeoLocationPoint location;
    private String name;
    private String type;
    private Boolean alert;

    public KafkaObject() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public GeoLocationPoint getLocation() {
        return location;
    }

    public void setLocation(GeoLocationPoint location) {
        this.location = location;
    }

    public Boolean getAlert() {
        return alert;
    }

    public void setAlert(Boolean alert) {
        this.alert = alert;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        JsonObject json = new JsonObject();
        json.addProperty("id",id);
        json.addProperty("name",name);
        json.addProperty("type",type);
        return json.toString();
    }
}
