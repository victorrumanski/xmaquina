package com.xmaquina.api.config;

import com.xmaquina.api.model.Sensor;

import java.time.Instant;
import java.util.List;

public interface SensorRepository {

    public List<Sensor> getAll();

    LatestSensorValue currentTemp(Long sensorId);

    List<LatestSensorValue> getLeituras(long sensorId, Instant start, Instant end);
}
