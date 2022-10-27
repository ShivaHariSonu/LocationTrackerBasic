package com.nsl.locationtracker.model;

public class UserInfoDto {
    private String id;
    private String name;
    private String type;
    private GeoLocationPoint location;
    private Long time;
    private Boolean alert;

    public UserInfoDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public GeoLocationPoint getLocation() {
        return location;
    }

    public void setLocation(GeoLocationPoint location) {
        this.location = location;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Boolean getAlert() {
        return alert;
    }

    public void setAlert(Boolean alert) {
        this.alert = alert;
    }
}
