package com.sda.forecast.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.config.Config;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class ForecastClient {

    private final ObjectMapper objectMapper;

    //API: https://openweathermap.org/api/one-call-api

    public ForecastClientResponse getForecastFromApi(Integer longitude, Integer latitude, LocalDate forecastDate) {
        log.info("Metoda getForecast została wywołana z parametrami - longitude: {}, latitude: {}, forecastDate: {}", longitude, latitude, forecastDate);
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("https://api.openweathermap.org/data/2.5/onecall?lat=" + latitude + "&lon=" + longitude + "&appid=" + Config.API_KEY + "&exclude=current,minutely,hourly&units=metric"))
                    .build();

            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String responseJson = httpResponse.body();

            ForecastClientResponseDTO forecastClientResponseDTO = objectMapper.readValue(responseJson, ForecastClientResponseDTO.class);
            List<ForecastClientResponseDTO.SingleForecastDTO> daily = forecastClientResponseDTO.getDaily();

            ForecastClientResponseDTO.SingleForecastDTO singleForecastDTO = daily.stream()
                    .filter(s -> convertApiTimestampToLocalDate(s.getTimestamp()).equals(forecastDate))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Nie znaleziono prognozy na dany dzień"));

            ForecastClientResponse forecastClientResponse = ForecastClientResponse.builder()
                    .forecastDate(convertApiTimestampToLocalDate(singleForecastDTO.getTimestamp()))
                    .temperature(singleForecastDTO.getTemperature().getDay())
                    .pressure(singleForecastDTO.getPressure())
                    .humidity(singleForecastDTO.getHumidity())
                    .windSpeed(singleForecastDTO.getWindSpeed())
                    .windDeg(singleForecastDTO.getWindDeg())
                    .build();

            return forecastClientResponse;
        } catch (Exception e) {
            log.error("Coś poszło nie tak: " + e.getMessage(), e);
            throw new RuntimeException("Pobranie informacji o pogodzie nie powiodło się: " + e.getMessage());
        }
    }

    public LocalDate convertApiTimestampToLocalDate(Long timestampApi) {
        Timestamp timestamp = new Timestamp(timestampApi * 1000);
        return timestamp.toLocalDateTime().toLocalDate();
    }
}