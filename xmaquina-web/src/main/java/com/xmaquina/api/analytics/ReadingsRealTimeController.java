package com.xmaquina.api.analytics;

import com.xmaquina.api.config.LatestSensorValue;
import com.xmaquina.api.config.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/sensores")
public class ReadingsRealTimeController {

    @Autowired
    SensorRepository sensorRepository;

    @GetMapping("/{sensor}/current")
    public ResponseEntity<LatestSensorValue> currentTemp(@PathVariable(value = "sensor") long sensorId) {
        LatestSensorValue latest = sensorRepository.currentTemp(sensorId);
        return new ResponseEntity<>(latest, HttpStatus.OK);
    }

    @GetMapping("/{sensor}/historic")
    public ResponseEntity<List<LatestSensorValue>> historicTemps(@PathVariable(value = "sensor") long sensorId) {
        List<LatestSensorValue> h = sensorRepository.getHistoricTemps(sensorId);
        return new ResponseEntity<>(h, HttpStatus.OK);
    }
}
