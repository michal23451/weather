package com.sda.forecast;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ForecastController {

    private final ForecastService forecastService;
    private final ObjectMapper objectMapper;
    private final ForecastMapper forecastMapper;

    //GET: /forecast?location={id}&date={day}
    //GET: /forecast?location={id}
    public String getForecast(long id, int day) {
        try {
            Forecast forecast = forecastService.getForecast(id, day);
            ForecastDTO forecastDTO = forecastMapper.mapForecastToForecastDTO(forecast);
            return objectMapper.writeValueAsString(forecastDTO);
        } catch (Exception e) {
            return "{\"errorMessage\": \"" + e.getMessage() + "\"}";
        }
    }

}
