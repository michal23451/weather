package com.sda.forecast;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

@Getter
@Setter
@Builder
@ToString
public class ForecastClientResponse {
    private Instant forecastDate;
    private Double temperature;
    private Integer pressure;
    private Integer humidity;
    private Double windSpeed;
    private Integer windDeg;
}
