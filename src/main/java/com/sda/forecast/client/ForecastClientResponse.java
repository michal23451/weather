package com.sda.forecast.client;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
public class ForecastClientResponse {
    private LocalDate forecastDate;
    private Double temperature;
    private Integer pressure;
    private Integer humidity;
    private Double windSpeed;
    private Integer windDeg;
}
