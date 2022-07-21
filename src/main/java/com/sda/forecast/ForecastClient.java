package com.sda.forecast;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;

import javax.persistence.criteria.CriteriaBuilder;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

public class ForecastClient {

    //API: https://openweathermap.org/api/one-call-api

    public ForecastClientResponse getForacast(Integer longitude, Integer latitude, LocalDate forecastDate) {
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            String API_KEY = "db064a229dcd833fde1a06ea34449c62";
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("https://api.openweathermap.org/data/2.5/onecall?lat=" + latitude + "&lon=" + longitude + "&appid=" + API_KEY + "&exclude=current,hourly&units=metric"))
                    .build();

            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String responseJson = httpResponse.body();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            ForecastClientResponseDTO forecastClientResponseDTO = objectMapper.readValue(responseJson, ForecastClientResponseDTO.class);
            List<ForecastClientResponseDTO.SingleForecastDTO> daily = forecastClientResponseDTO.getDaily();
            ForecastClientResponseDTO.SingleForecastDTO singleForecastDTO = daily.stream()
                    .filter(s -> convertApiTimeToLocalDate(s.getTimestamp()).equals(forecastDate))
                    .findFirst().
                    get();

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
            //e.printStackTrace();
            throw new RuntimeException("Pobranie informacji o pogodzie nie powiodło się: " + e.getMessage());
        }
    }

    public LocalDate convertApiTimeToLocalDate(Long instatnInSecond) {
        return Instant.ofEpochSecond(instatnInSecond).atZone(ZoneId.of("UTC")).toLocalDate();
    }


}
