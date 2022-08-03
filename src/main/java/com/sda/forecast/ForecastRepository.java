package com.sda.forecast;

import com.sda.location.Location;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public interface ForecastRepository {
    Optional<Forecast> getActiveForecast(Location location, LocalDate forecastDate, LocalDateTime createdDate);

    Forecast save (Forecast forecast);

}
