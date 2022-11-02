package com.nsl.locationtracker.controller;

import com.nsl.locationtracker.model.KafkaObject;
import com.nsl.locationtracker.model.UserLocationGIS;
import com.nsl.locationtracker.service.impl.LocationTrackingServiceImpl;
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
    private LocationTrackingServiceImpl locationTrackingServiceImpl;

    @PostMapping(value = "/saveelastic",produces = "application/json")
    public KafkaObject saveUserInfoInES(@RequestBody KafkaObject kafkaObject){
        KafkaObject result = null;
        try {
            result = locationTrackingServiceImpl.saveelastic(kafkaObject);

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
            result = locationTrackingServiceImpl.saveGIS(kafkaObject);

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
            result = locationTrackingServiceImpl.getUserInfo();

        }
        catch (Exception ex){
            LOGGER.error("Error while saving the info",ex.getMessage());
        }
        return result;
    }

}
