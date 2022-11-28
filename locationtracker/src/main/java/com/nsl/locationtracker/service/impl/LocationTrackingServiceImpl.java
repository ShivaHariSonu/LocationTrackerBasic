package com.nsl.locationtracker.service.impl;

import com.nsl.locationtracker.dto.UserLocationDto;
import com.nsl.locationtracker.model.*;
import com.nsl.locationtracker.repository.PostGISRepository;
import com.nsl.locationtracker.repository.UserLocationRepository;
import com.nsl.locationtracker.service.abs.LocationTrackingService;
import com.nsl.locationtracker.util.ObjectDtoConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;

@Service
public class LocationTrackingServiceImpl implements LocationTrackingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LocationTrackingServiceImpl.class);
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectDtoConverter objectDtoConverter;

    @Autowired
    private PostGISRepository postGISRepository;

    @Autowired
    private UserLocationRepository userLocationRepository;

    @Value(value = "${kafka.topic.locationtrack.out}")
    public String outputtopic;


    @Override
    public void sendMessage(List<UserLocationGIS> users) {

        for(UserLocationGIS foreachUser:users) {
            String message = objectDtoConverter.GISToKafka(foreachUser);
            ListenableFuture<SendResult<String, String>> future =
                    kafkaTemplate.send(outputtopic, message);
            future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
                @Override
                public void onSuccess(SendResult<String, String> result) {
                    LOGGER.info("Sent message=[{}]", message);
                }
                @Override
                public void onFailure(Throwable ex) {
                    LOGGER.info("Unable to send message=[{}] due to : {}", message, ex.getMessage());
                }
            });
        }
    }

    @Override
    public KafkaObject saveelastic(KafkaObject kafkaObject){
        UserLocationES userLocationES = objectDtoConverter.convertUserLocationUtil(kafkaObject);
        //TODO Implement ES Save method
        return new KafkaObject();
    }

    @Override
    public UserLocationGIS saveGIS(KafkaObject kafkaObject) {
        UserLocationGIS pointGIS = objectDtoConverter.kafkatoGIS(kafkaObject);
        return postGISRepository.save(pointGIS);
    }

    @Override
    public List<UserLocationGIS> getNearByTrackers(GeoLocationPoint currentPoint, Double radius, Integer limit) {
        return postGISRepository.findAllTrackersByDistanceFromUser(currentPoint.getLat(), currentPoint.getLon(),limit);
    }

    @Override
    public List<UserLocationGIS> getUserInfo() {
        return (List<UserLocationGIS>) postGISRepository.findAll();
    }

    @Override
    public List<UserLocation> getUserLocation() {
        return userLocationRepository.findAll();
    }
    @Override
    public List<UserLocation> getNearByLocations(UserLocationDto userLocationDto) {

        return userLocationRepository.findAllNearByUsersOne(userLocationDto.getLongitude(),
                userLocationDto.getLatitude(),
                userLocationDto.getRadius(),
                userLocationDto.getLimit(),
                userLocationDto.getTenantid(),
                userLocationDto.getLastminutes());
    }
}
