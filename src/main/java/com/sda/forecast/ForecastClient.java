package com.sda.forecast;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Slf4j
public class ForecastClient {

    private static final String API_KEY = "db064a229dcd833fde1a06ea34449c62";
    private final ObjectMapper objectMapper = new ObjectMapper(); // todo inject it

    //API: https://openweathermap.org/api/one-call-api

    public ForecastClientResponse getForecast(Integer longitude, Integer latitude, LocalDate forecastDate) {
        log.info("Metoda getForecast została wywołana z parametrami - longitude: {}, latitude: {}, forecastDate: {}", longitude, latitude, forecastDate);
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("https://api.openweathermap.org/data/2.5/onecall?lat=" + latitude + "&lon=" + longitude + "&appid=" + API_KEY + "&exclude=current,hourly&units=metric"))
                    .build();

            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String responseJson = httpResponse.body();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // todo move it

            ForecastClientResponseDTO forecastClientResponseDTO = objectMapper.readValue(responseJson, ForecastClientResponseDTO.class);
            List<ForecastClientResponseDTO.SingleForecastDTO> daily = forecastClientResponseDTO.getDaily();
            ForecastClientResponseDTO.SingleForecastDTO singleForecastDTO = daily.stream()
                    .filter(s -> convertApiTimeToLocalDate(s.getTimestamp()).equals(forecastDate))
                    .findFirst()
                    .get(); // todo .orElseThrow(() -> new RuntimeException("daily list was not returned"));

            ForecastClientResponse forecastClientResponse = ForecastClientResponse.builder()
                    .forecastDate(Instant.ofEpochSecond(singleForecastDTO.getTimestamp()))
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

    public LocalDate convertApiTimeToLocalDate(Long timestamp) {
        return Instant.ofEpochSecond(timestamp).atZone(ZoneId.of("UTC")).toLocalDate();
    }
}
