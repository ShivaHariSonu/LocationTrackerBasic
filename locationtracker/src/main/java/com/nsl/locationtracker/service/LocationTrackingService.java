package com.nsl.locationtracker.service;

import com.nsl.locationtracker.listener.TrackingListener;
import com.nsl.locationtracker.model.UserInfoDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class LocationTrackingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LocationTrackingService.class);
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value(value = "${kafka.topic.locationtrack.out}")
    public String outputtopic;


    public void sendMessage(String message) {
        LOGGER.info("Sending message {}",message);
        ListenableFuture<SendResult<String, String>> future =
                kafkaTemplate.send(outputtopic, message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                LOGGER.info("Sent message=[{}]",message);
            }
            @Override
            public void onFailure(Throwable ex) {
                LOGGER.info("Unable to send message=[{}] due to : {}",message,ex.getMessage());
            }
        });
    }

    public UserInfoDto save(UserInfoDto userInfoDto){

    }

}
