package com.xmaquina.api.analytics;

import com.xmaquina.api.config.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.stream.Collectors;

@Controller
public class ReadingsAnalyticsController {

    @Autowired
    SensorRepository sensorRepository;

    @GetMapping("/")
    public String showHome(Model model) {
        model.addAttribute("sensores", sensorRepository.getAll().stream().sorted().collect(Collectors.toList()));
        return "index";
    }
}
