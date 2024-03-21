package com.xmaquina.api.config;

import com.xmaquina.api.model.Sensor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class SensorMapper implements RowMapper<Sensor> {

    @Override
    public Sensor mapRow(ResultSet rs, int i) throws SQLException {
        Sensor sensor = new Sensor();
        sensor.id = rs.getLong("sensor_id");
        sensor.leitura = rs.getBigDecimal("temperature");
        Date d = new Date(rs.getTimestamp("timestamp").getTime());
        Instant instant = d.toInstant();
        instant = instant.truncatedTo(ChronoUnit.SECONDS);
        sensor.ts = instant.toEpochMilli();
        sensor.date = rs.getTimestamp("timestamp").toLocalDateTime();
        return sensor;
    }
}