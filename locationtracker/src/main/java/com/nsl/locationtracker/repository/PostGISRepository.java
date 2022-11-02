package com.nsl.locationtracker.repository;

import com.nsl.locationtracker.model.UserLocationGIS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
public interface PostGISRepository extends JpaRepository<UserLocationGIS,Integer>{

    @Query(value = "SELECT name,type,ST_AsText(location) as location"
            + "FROM user_location_gis"
            + "WHERE type ='TRACKER'"
            + "ORDER BY location <-> ST_Point(:userLat,:userLon)"
            + "LIMIT :limit"
            , nativeQuery = true)
    List<UserLocationGIS> findAllTrackersByDistanceFromUser(@Param("userLat")  Double userLatitude,
                                                            @Param("userLon") Double userLongitude,
                                                            @Param("limit") Integer limit);
}
