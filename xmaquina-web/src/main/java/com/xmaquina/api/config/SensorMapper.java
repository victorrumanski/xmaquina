package com.xmaquina.api.config;

import com.xmaquina.api.model.Sensor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SensorMapper implements RowMapper<Sensor> {

    @Override
    public Sensor mapRow(ResultSet rs, int i) throws SQLException {
        Sensor employee = new Sensor();
        employee.id = rs.getLong("sensor_id");
        employee.ts = rs.getTimestamp("timestamp").toLocalDateTime();
        employee.leitura = rs.getBigDecimal("temperature");
        return employee;
    }
}