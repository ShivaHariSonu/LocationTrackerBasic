package com.nsl.locationtracker.model;


import com.bedatadriven.jackson.datatype.jts.serialization.GeometryDeserializer;
import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import org.hibernate.annotations.Type;
import org.postgis.PGgeometry;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="user_location_gis")
public class UserLocationGIS implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(columnDefinition = "serial",name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;


    @Column(columnDefinition = "geometry(Point,4326)")
    private Geometry location;

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


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Geometry getLocation() {
        return location;
    }

    public void setLocation(Geometry location) {
        this.location = location;
    }
}
