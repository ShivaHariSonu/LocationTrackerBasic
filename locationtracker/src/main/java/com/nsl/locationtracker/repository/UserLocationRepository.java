package com.nsl.locationtracker.repository;

import com.nsl.locationtracker.dto.UserLocationDto;
import com.nsl.locationtracker.model.UserLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserLocationRepository extends JpaRepository<UserLocation,Integer> {
    @Query(value = "SELECT *,point(?1, ?2) <@> point(longitude, latitude) as distance "
            + "FROM user_location "
            + "WHERE (point(?1, ?2) <@> point(longitude, latitude)) < ?3 "
            + "and tenantid =?5 "
            + "and (extract(epoch from now())*1000 -lastupdatedat)<1000*60* ?6 "
            + "ORDER BY distance LIMIT ?4"
            , nativeQuery = true)
    List<UserLocation> findAllNearByUsers(Double longitude, Double latitude, Integer radius, Integer limit, String tenantId,Integer lastminutes);

    @Query(value = "SELECT *, ROUND(earth_distance(ll_to_earth(?2,?1), ll_to_earth(latitude, longitude))::DECIMAL, 2) AS distance "
            + "FROM user_location "
            + "WHERE earth_box(ll_to_earth (?2,?1), 1000 * ?3) @> ll_to_earth (latitude, longitude) "
            + "AND earth_distance(ll_to_earth (?2,?1), ll_to_earth (latitude, longitude)) < 1000 * ?3 "
            + "AND (extract(epoch from now())*1000 -lastupdatedat)<1000*60* ?6 "
            + "AND tenantid =?5 "
            + "ORDER BY distance LIMIT ?4"
            , nativeQuery = true)
    List<UserLocation> findAllNearByUsersOne(Double longitude, Double latitude, Integer radius, Integer limit, String tenantId,Integer lastminutes);
}
