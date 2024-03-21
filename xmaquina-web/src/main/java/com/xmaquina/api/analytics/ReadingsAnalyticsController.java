package com.xmaquina.api.analytics;

import com.xmaquina.api.config.LatestSensorValue;
import com.xmaquina.api.config.SensorRepository;
import com.xmaquina.api.model.Sensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/leituras")
public class ReadingsAnalyticsController {

    @Autowired
    SensorRepository sensorRepository;

    @GetMapping("/{sensor}/current")
    public ResponseEntity<LatestSensorValue> currentTemp(@PathVariable(value = "sensor") long sensorId) {
        LatestSensorValue latest = sensorRepository.currentTemp(sensorId);
        return new ResponseEntity<>(latest, HttpStatus.OK);
    }

    @GetMapping("/current")
    public ResponseEntity<List<Sensor>> currentTempAllSensors() {
        List<Sensor> collect = sensorRepository.getAll().stream().sorted().collect(Collectors.toList());
        return new ResponseEntity<>(collect, HttpStatus.OK);

    }

    private static final DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss z");

    @GetMapping("/{sensor}/leituras")
    public ResponseEntity<List<LatestSensorValue>> leituras(
            @PathVariable(value = "sensor") long sensorId
            , @RequestParam(value = "start") String start
            , @RequestParam(value = "end") String end
    ) {
        List<LatestSensorValue> h = sensorRepository.getLeituras(sensorId,
                ZonedDateTime.parse(start + " UTC", df).toInstant(),
                ZonedDateTime.parse(end + " UTC", df).toInstant()
        );
        return new ResponseEntity<>(h, HttpStatus.OK);
    }

}
