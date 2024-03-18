package com.xmaquina.api.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Reading {

    public LocalDateTime createdAt;
    public Long sensorId;
    public BigDecimal value;
}
