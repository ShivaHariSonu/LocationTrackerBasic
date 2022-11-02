package com.nsl.locationtracker.util;

import com.nsl.locationtracker.model.KafkaObject;
import com.nsl.locationtracker.model.UserLocationES;
import com.nsl.locationtracker.model.UserLocationGIS;
import org.postgis.Point;
import org.springframework.stereotype.Component;

@Component
public class ObjectDtoConverter {
    public UserLocationES convertUserLocationUtil(KafkaObject kafkaObject){
        UserLocationES userLocationES = new UserLocationES();
        userLocationES.setId(kafkaObject.getId().toString());
        userLocationES.setLocation(userLocationES.getLocation());
        return userLocationES;
    }
    public UserLocationGIS kafkatoGIS(KafkaObject kafkaObject){
        UserLocationGIS userLocationGIS = new UserLocationGIS();
        userLocationGIS.setId(kafkaObject.getId());
        Point pointGIS = new Point(kafkaObject.getLocation().getLat(), kafkaObject.getLocation().getLon());
        userLocationGIS.setLocation(pointGIS);
        userLocationGIS.setName(kafkaObject.getName());
        userLocationGIS.setType(kafkaObject.getType());
        return userLocationGIS;
    }
    public String GISToKafka(UserLocationGIS userLocationGIS){
        KafkaObject kafkaObject = new KafkaObject();
        kafkaObject.setId(userLocationGIS.getId());
        kafkaObject.setName(userLocationGIS.getName());
        kafkaObject.setType(userLocationGIS.getType());
        return kafkaObject.toString();
    }
}
