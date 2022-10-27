package com.nsl.locationtracker.model;

import com.google.gson.annotations.SerializedName;

public class GeoLocationPoint {
    @SerializedName(value = "lat",
    alternate = "latitude")
    private double lat;
    @SerializedName(value = "lon",alternate = "longitude")
    private double lon;

    public GeoLocationPoint() {
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
