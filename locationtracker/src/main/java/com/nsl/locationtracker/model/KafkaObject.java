package com.nsl.locationtracker.model;

import com.google.gson.JsonObject;

public class KafkaObject {
    private Long id;
    private GeoLocationPoint location;
    private String name;
    private UserType type;
    private Boolean alert;

    public KafkaObject() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        JsonObject json = new JsonObject();
        json.addProperty("id",id);
        json.addProperty("name",name);
        json.addProperty("type",type.toString());
        return json.toString();
    }
}