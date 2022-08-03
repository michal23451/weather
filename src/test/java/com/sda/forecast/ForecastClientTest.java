package com.sda.forecast;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.forecast.client.ForecastClient;
import com.sda.forecast.client.ForecastClientResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class ForecastClientTest {

    ForecastClient forecastClient;
    ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        forecastClient = new ForecastClient(objectMapper);
    }

    @Test
    void getForecast_returnsCorrectForecast() {
        // given
        // when
        ForecastClientResponse result = forecastClient.getForecastFromApi(19, 50, LocalDate.now().plusDays(2));
        // then
        log.info(result.toString());
        assertThat(result).isNotNull();
    }
}
