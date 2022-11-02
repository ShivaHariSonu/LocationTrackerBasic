package com.nsl.locationtracker.service.abs;

import com.nsl.locationtracker.model.GeoLocationPoint;
import com.nsl.locationtracker.model.KafkaObject;
import com.nsl.locationtracker.model.UserLocationGIS;

import java.util.List;

public interface LocationTrackingService{

    void sendMessage(List<UserLocationGIS> users);

    KafkaObject saveelastic(KafkaObject kafkaObject);

    UserLocationGIS saveGIS(KafkaObject kafkaObject);

    List<UserLocationGIS> getNearByTrackers(GeoLocationPoint currentPoint,Double radius, Integer limit);
}
