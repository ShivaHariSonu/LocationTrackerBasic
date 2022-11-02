package com.nsl.locationtracker.listener;

import com.google.gson.GsonBuilder;
import com.nsl.locationtracker.model.KafkaObject;
import com.nsl.locationtracker.model.UserLocationGIS;
import com.nsl.locationtracker.service.abs.LocationTrackingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.List;

public class TrackingListener {
    private static final Logger log = LoggerFactory.getLogger(TrackingListener.class);

    @Autowired
    private LocationTrackingService locationTrackingService;

    @KafkaListener(topics = "${kafka.topic.locationtrack.in}", groupId = "${spring.kafka.consumer.group-id}")
    void trackingListener(@Payload String userinfo) {
        try {
            KafkaObject kafkaObject = new GsonBuilder().create().fromJson(userinfo, KafkaObject.class);
            log.info("Received the payload:-"+ kafkaObject);
            if(kafkaObject ==null || kafkaObject.getId()==null || kafkaObject.getLocation()==null){
                log.error("Invalid input");
                return;
            }
            if (kafkaObject.getAlert()) {
                List<UserLocationGIS> nearByPoints = locationTrackingService.getNearByTrackers(kafkaObject.getLocation(),10.0,2);
                locationTrackingService.sendMessage(nearByPoints);
            }
            else {
                //update in PostGIS
                UserLocationGIS userLocationGIS = locationTrackingService.saveGIS(kafkaObject);
                log.info("Saved postgis data for user of ID:{}",userLocationGIS.getId());
            }
        } catch (Exception e) {
            log.error("Error while analysing the user location "+e.getMessage());
        }
    }
}
