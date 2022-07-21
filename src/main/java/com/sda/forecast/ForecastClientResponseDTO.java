package com.sda.forecast;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
public class ForecastClientResponseDTO {

    private List<SingleForecastDTO> daily;

    @Getter
    @Setter
    @ToString
    public static class SingleForecastDTO {
        @JsonProperty("dt")
        private Long timestamp;
        @JsonProperty("temp")
        private TemperatureDTO temperature;
        private Integer pressure;
        private Integer humidity;
        @JsonProperty("wind_speed")
        private Double windSpeed;
        @JsonProperty("wind_deg")
        private Integer windDeg;

        @Getter
        @Setter
        @ToString
        public static class TemperatureDTO {
            private Double day;
        }
    }
}
