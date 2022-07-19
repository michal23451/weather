package com.sda;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ForecastDTO {
    private Long id;
    private float temperature;
    private int pressure;
    private int humidity;
    private int windSpeed;
    private int windDirection;
    private Location location;
    private Instant forecastDate;
}