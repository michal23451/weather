package com.sda.forecast;

import com.sda.location.Location;
import com.sda.location.LocationDBRepository;
import com.sda.location.LocationRepository;
import lombok.RequiredArgsConstructor;

import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
public class ForecastService {

    private final LocationRepository locationRepository;
    private final ForecastRepository forecastRepository;

    // TODO: 21.07.2022  
    public Forecast getForecast(Long locationId, Integer day) {
        Optional<Location> locationOptional = locationRepository.findById(locationId);
        Location location = new Location();

        if (locationOptional.isEmpty()) {
            throw new NoSuchElementException("Nie znaleziono lokalizacji o podanym id!");
        } else {
            location = locationOptional.get(); // todo locationOptional.oreElseThrow(() -> new NoSuchElementException())
        }

        if (day == null || day == 0) {
            day = 1;
            System.out.println("Ustawiono dzień na 1"); // todo use logger
        }
        if (day < 0 || day > 7) {
            throw new IllegalArgumentException("Należy podać dzień z przedziału <1,7>");
        }

        return null;
    }
}
