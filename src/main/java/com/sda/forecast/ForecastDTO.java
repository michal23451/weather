package com.sda.forecast;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ForecastDTO {
    private long id;
    private double temperature;
    private int pressure;
    private int humidity;
    private double windSpeed;
    private String windDirection;
}