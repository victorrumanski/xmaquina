package com.xmaquina.api.config;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class LatestSensorValue {
    public Long sensorId;
    public BigDecimal value;
    public Long ts;
    public String date;

    public LatestSensorValue(Long sensorId, BigDecimal value, Long ts, String dt) {
        this.sensorId = sensorId;
        this.value = value;
        Date d = new Date(ts);
        Instant instant = d.toInstant();
        instant = instant.truncatedTo(ChronoUnit.SECONDS);
        this.ts = instant.toEpochMilli();
        this.date = dt;
    }
}