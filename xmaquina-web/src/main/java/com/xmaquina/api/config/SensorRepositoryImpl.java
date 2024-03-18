package com.xmaquina.api.config;

import com.xmaquina.api.model.Sensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
public class SensorRepositoryImpl implements SensorRepository {
    JdbcTemplate jdbcTemplate;

    @Autowired
    public SensorRepositoryImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final String SQL_FIND_ALL = "SELECT sensor_id,timestamp,temperature from leituras LATEST BY sensor_id order by sensor_id";
    private final String SQL_FIND_CURRENT_TEMP = "SELECT timestamp,temperature from leituras LATEST BY sensor_id where sensor_id=?";
    private final String SQL_FIND_HISTORIC_TEMP = "SELECT timestamp,temperature from leituras WHERE sensor_id=? ORDER by timestamp desc limit 100";

    public List<Sensor> getAll() {
        return jdbcTemplate.query(SQL_FIND_ALL, new SensorMapper());
    }

    @Override
    public LatestSensorValue currentTemp(Long sensorId) {
        return jdbcTemplate.queryForObject(SQL_FIND_CURRENT_TEMP, new Object[]{sensorId}, (rs, rowNum) ->
                new LatestSensorValue(sensorId,
                        rs.getBigDecimal("temperature").setScale(2 , RoundingMode.HALF_UP),
                        rs.getTimestamp("timestamp").getTime(),
                        rs.getTimestamp("timestamp").toLocalDateTime().toString()
                ));
    }

    @Override
    public List<LatestSensorValue> getHistoricTemps(long sensorId) {
        return jdbcTemplate.query(SQL_FIND_HISTORIC_TEMP, new Object[]{sensorId}, (rs, rowNum) ->
                new LatestSensorValue(sensorId,
                        rs.getBigDecimal("temperature").setScale(2 , RoundingMode.HALF_UP),
                        rs.getTimestamp("timestamp").getTime(),
                        rs.getTimestamp("timestamp").toLocalDateTime().toString()
                ));
    }

}
