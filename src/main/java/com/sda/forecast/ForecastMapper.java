package com.sda.forecast;

import com.sda.forecast.client.ForecastClientResponse;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;


@RequiredArgsConstructor
public class ForecastMapper {

    private final WindUnitMapper windUnitMapper;

    Forecast mapForecastClientResponseToForecast(ForecastClientResponse forecastClientResponse) {
        Forecast forecast = new Forecast();
        forecast.setTemperature(forecastClientResponse.getTemperature());
        forecast.setPressure(forecastClientResponse.getPressure());
        forecast.setHumidity(forecastClientResponse.getHumidity());
        forecast.setWindSpeed(forecastClientResponse.getWindSpeed());
        forecast.setWindDirection(forecastClientResponse.getWindDeg());
        forecast.setForecastDate(forecastClientResponse.getForecastDate());
        forecast.setCreatedDate(LocalDateTime.now());
        return forecast;
    }

    ForecastDTO mapForecastToForecastDTO(Forecast forecast) {
        ForecastDTO forecastDTO = new ForecastDTO();
        forecastDTO.setId(forecast.getId());
        forecastDTO.setTemperature(forecast.getTemperature());
        forecastDTO.setPressure(forecast.getPressure());
        forecastDTO.setHumidity(forecast.getHumidity());
        forecastDTO.setWindSpeed(forecast.getWindSpeed());
        forecastDTO.setWindDirection(windUnitMapper.mapWindDegreeToDirection(forecast.getWindDirection()));
        return forecastDTO;
    }
}
