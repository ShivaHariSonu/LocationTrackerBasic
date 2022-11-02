package com.nsl.locationtracker.repository;

import com.nsl.locationtracker.model.UserLocationGIS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostGISRepository extends JpaRepository<UserLocationGIS,Long>{

    @Query(value = "SELECT nhf.id, nhf.name, nhf.geom, ST_Distance(nhf.geom,ST_SetSRID(ST_Point(:userLongitude,:userLatitude),4326)) AS distance "
            + "FROM nairobi_Health_facilities nhf "
            + "ORDER BY nhf.geom  <-> ST_SetSRID(ST_Point(:userLongitude,:userLatitude),4326) "
            + "LIMIT :limit"
            , nativeQuery = true)
    List<UserLocationGIS> findAllTrackersByDistanceFromUser(@Param("userLon") Double userLongitude,
                                                            @Param("userLat")  Double userLatitude,
                                                            @Param("radius")  Double radius,
                                                            @Param("limit") Integer limit);
}
