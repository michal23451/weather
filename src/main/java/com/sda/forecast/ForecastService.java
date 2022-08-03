package com.sda.forecast;

import com.sda.forecast.client.ForecastClient;
import com.sda.forecast.client.ForecastClientResponse;
import com.sda.location.Location;
import com.sda.location.LocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class ForecastService {

    private final ForecastRepository forecastRepository;
    private final LocationService locationService;
    private final ForecastClient forecastClient;
    private final ForecastMapper forecastMapper;

    Forecast getForecast(Long locationId, int day) {
        Location location = locationService.getById(locationId).orElseThrow(() -> new NoSuchElementException("Nie znaleziono lokalizacji o podanym id!"));
        Forecast forecast = new Forecast();


        if (day == 0) {
            day = 1;
            log.info("Ustawiono dzień na 1");
        }

        //lub
        //day = (day == 0 ? 1 : day);

        if (day < 1 || day > 5) {
            throw new IllegalArgumentException("Należy podać dzień z przedziału <1,5>");
        }

        LocalDate forecastDate = LocalDate.now().plusDays(day);
        LocalDateTime expirationDate = LocalDateTime.now().minusDays(1); //prognoza w DB ważna jest ma być 24h

        Optional<Forecast> activeForecast = forecastRepository.getActiveForecast(location, forecastDate, expirationDate);

        if (activeForecast.isEmpty()) {
            log.info("pobrano dane o prognozie pogody z API");
            ForecastClientResponse forecastClientResponse = forecastClient.getForecastFromApi(location.getLongitude(), location.getLatitude(), forecastDate);
            forecast = forecastMapper.mapForecastClientResponseToForecast(forecastClientResponse);
            location.addForecast(forecast);
            forecast = forecastRepository.save(forecast);
        } else {
            log.info("pobrano dane o prognozie pogody z DB");
            forecast = activeForecast.orElseThrow(() -> new NoSuchElementException("brak prognozy w DB!!!"));
        }

        return forecast;
    }


}
