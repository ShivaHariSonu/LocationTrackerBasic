package com.nsl.locationtracker.dto;

import javax.persistence.Column;

public class UserLocationDto {

    private Integer id;
    private Long userid;
    private String name;
    private String email;
    private String tenantid;
    private Long lastupdatedat;
    private Double longitude;

    private Double latitude;

    private Double distance;

    private Integer lastminutes;
    private Integer radius;
    private Integer limit;

    public UserLocationDto() {
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Integer getRadius() {
        return radius;
    }

    public void setRadius(Integer radius) {
        this.radius = radius;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getTenantid() {
        return tenantid;
    }

    public void setTenantid(String tenantid) {
        this.tenantid = tenantid;
    }

    public Long getLastupdatedat() {
        return lastupdatedat;
    }

    public void setLastupdatedat(Long lastupdatedat) {
        this.lastupdatedat = lastupdatedat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Integer getLastminutes() {
        return lastminutes;
    }

    public void setLastminutes(Integer lastminutes) {
        this.lastminutes = lastminutes;
    }
}
