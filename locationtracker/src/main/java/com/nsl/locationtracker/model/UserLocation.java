package com.nsl.locationtracker.model;

import com.vividsolutions.jts.geom.Geometry;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_location")
public class UserLocation {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(columnDefinition = "serial",name = "id")
    private Integer id;

    @Column(name = "userid")
    private Long userId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "tenantid")
    private String tenantId;

    @Column(name = "lastupdatedat")
    private Long lastUpdatedAt;

    @Column(nullable = false,name = "longitude")
    private Double longitude;

    @Column(nullable = false,name = "latitude")
    private Double latitude;

    //private Double distance;

    public UserLocation() {
    }

//    public Double getDistance() {
//        return distance;
//    }
//
//    public void setDistance(Double distance) {
//        this.distance = distance;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public Long getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(Long lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
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
}
