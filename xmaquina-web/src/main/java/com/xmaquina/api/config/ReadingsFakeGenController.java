package com.xmaquina.api.config;

import com.xmaquina.api.config.mqtt.MqttSample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fakegen")
public class ReadingsFakeGenController {

    @Autowired
    MqttSample mqttSample;

    @GetMapping("/start/{sensor}")
    public ResponseEntity<String> start(@PathVariable(value = "sensor") long sensorId) throws InterruptedException {
        mqttSample.start(sensorId);
        return new ResponseEntity<>("Started", HttpStatus.OK);
    }

    @GetMapping("/stop")
    public ResponseEntity<String> stop() {
        mqttSample.stop();
        return new ResponseEntity<>("Stopped", HttpStatus.OK);
    }

}
