package com.sda;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExternalForacastClient {

    //API //https://openweathermap.org/forecast5

    public String getForacast(Integer longitude, Integer latitude, Integer day) {
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            String API_KEY = "db064a229dcd833fde1a06ea34449c62";
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("https://api.openweathermap.org/data/2.5/forecast?lang=pl&units=metric&lat=" + latitude + "&lon=" + longitude + "&appid=" + API_KEY))
                    .build();

            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String responseJson = httpResponse.body();
            return responseJson;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Pobranie informacji o pogodzie nie powiodło się: " + e.getMessage());
        }
    }
}
