package com.nsl.locationtracker.controller;

import com.nsl.locationtracker.model.UserInfoDto;
import com.nsl.locationtracker.service.LocationTrackingService;
import org.elasticsearch.action.explain.ExplainAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("location-track")
public class LocationTrackingController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LocationTrackingController.class);

    @Autowired
    private LocationTrackingService locationTrackingService;

    @PostMapping(value = "/save",produces = "application/json")
    public UserInfoDto saveUserInfo(@RequestBody UserInfoDto userInfoDto){
        UserInfoDto result = null;
        try {
            result = locationTrackingService.save(userInfoDto);

        }
        catch (Exception ex){
            LOGGER.error("Error while saving the info",ex.getMessage());
        }
        return result;
    }

}
