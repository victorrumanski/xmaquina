package com.xmaquina.api.config;

import com.xmaquina.api.model.Sensor;

import java.math.BigDecimal;
import java.util.List;

public interface SensorRepository  {

    public List<Sensor> getAll();

    LatestSensorValue currentTemp(Long sensorId);

    List<LatestSensorValue> getHistoricTemps(long sensorId);
}
