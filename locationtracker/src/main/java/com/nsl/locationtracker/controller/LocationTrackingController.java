package com.nsl.locationtracker.controller;

import com.nsl.locationtracker.dto.UserLocationDto;
import com.nsl.locationtracker.model.KafkaObject;
import com.nsl.locationtracker.model.UserLocation;
import com.nsl.locationtracker.model.UserLocationGIS;
import com.nsl.locationtracker.service.abs.LocationTrackingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/location-track")
public class LocationTrackingController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LocationTrackingController.class);

    @Autowired
    private LocationTrackingService locationTrackingService;

    @PostMapping(value = "/saveelastic",produces = "application/json")
    public KafkaObject saveUserInfoInES(@RequestBody KafkaObject kafkaObject){
        KafkaObject result = null;
        try {
            result = locationTrackingService.saveelastic(kafkaObject);

        }
        catch (Exception ex){
            LOGGER.error("Error while saving the info",ex.getMessage());
        }
        return result;
    }
    @PostMapping(value = "/savepostgis",produces = "application/json")
    public UserLocationGIS saveUserInfoInGIS(@RequestBody KafkaObject kafkaObject){
        UserLocationGIS result = null;
        try {
            result = locationTrackingService.saveGIS(kafkaObject);

        }
        catch (Exception ex){
            LOGGER.error("Error while saving the info",ex.getMessage());
        }
        return result;
    }
    @GetMapping(value = "/getpostgis",produces = "application/json")
    public List<UserLocationGIS> getUserInfoListInGIS(){
        List<UserLocationGIS> result = null;
        try {
            result = locationTrackingService.getUserInfo();

        }
        catch (Exception ex){
            LOGGER.error("Error while saving the info",ex.getMessage());
        }
        return result;
    }


    @GetMapping(value = "/getuserlocation",produces = "application/json")
    public List<UserLocation> getUserLocations(){
        List<UserLocation> result = null;
        try {
            result = locationTrackingService.getUserLocation();

        }
        catch (Exception ex){
            LOGGER.error("Error while saving the info",ex.getMessage());
        }
        return result;
    }
    @GetMapping(value = "/getuserlocationnearby",produces = "application/json")
    public List<UserLocation> getUserLocationNearby(@RequestBody UserLocationDto userLocationDto){
        List<UserLocation> result = null;
        try {
            result = locationTrackingService.getNearByLocations(userLocationDto);

        }
        catch (Exception ex){
            LOGGER.error("Error while saving the info",ex.getMessage());
        }
        return result;
    }
}
