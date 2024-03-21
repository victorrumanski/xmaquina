package com.xmaquina.api.analytics;

import com.xmaquina.api.config.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Controller
public class ReadingsAnalyticsPageController {

    @Autowired
    SensorRepository sensorRepository;

    @GetMapping("/")
    public String showHome(Model model) {
        model.addAttribute("startDate", LocalDateTime.now().truncatedTo(ChronoUnit.DAYS));
        model.addAttribute("endDate", LocalDateTime.now().withHour(23).withMinute(59).withSecond(59));
        model.addAttribute("sensores", sensorRepository.getAll().stream().sorted().collect(Collectors.toList()));
        return "index";
    }
}
