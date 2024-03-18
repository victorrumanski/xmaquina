package com.xmaquina.api.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Sensor implements Comparable {
    public Long id;
    public String name;
    public LocalDateTime ts;
    public BigDecimal leitura;

    @Override
    public String toString() {
        return id + "";
    }

    @Override
    public int compareTo(Object o) {
        return -((Sensor) o).id.compareTo(this.id);
    }
}
