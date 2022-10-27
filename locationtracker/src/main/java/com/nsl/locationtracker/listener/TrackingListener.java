package com.nsl.locationtracker.listener;

import com.google.gson.GsonBuilder;
import com.nsl.locationtracker.model.UserInfoDto;
import com.nsl.locationtracker.service.LocationTrackingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.UUID;

public class TrackingListener {
    private static final Logger log = LoggerFactory.getLogger(TrackingListener.class);

    @Autowired
    LocationTrackingService locationTrackingService;

    @KafkaListener(topics = "${kafka.topic.locationtrack.in}", groupId = "${spring.kafka.consumer.group-id}")
    void trackingListener(@Payload String userinfo) {
        try {
            UserInfoDto userInfoDto = new GsonBuilder().create().fromJson(userinfo,UserInfoDto.class);
            log.info("Received the payload:-"+ userInfoDto);
            if(userInfoDto==null || userInfoDto.getId()==null || userInfoDto.getLocation()==null){
                log.error("Invalid input");
                return;
            }
            if (userInfoDto.getAlert()) {
                //Query ES and return latest police info
                log.info("Alerting the Police");
                locationTrackingService.sendMessage("Alert the police");
            }
            else {
                //update in ES


            }
        } catch (Exception e) {
            log.error("Error while analysing the user location "+e.getMessage());
        }
    }
}
