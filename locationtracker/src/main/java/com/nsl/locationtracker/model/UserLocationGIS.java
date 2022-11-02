package com.nsl.locationtracker.model;

import org.postgis.Point;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="user_location_gis")
public class UserLocationGIS implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Integer id;

    private String name;

    private UserType type;

    @Column(name = "location")//@Column(columnDefinition = "geometry(Point,4326)")
    private Point location;

    public UserLocationGIS() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }
}
