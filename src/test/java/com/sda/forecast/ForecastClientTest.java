package com.sda.forecast;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class ForecastClientTest {

    @Test
    void getForecastClient_returnsCorrectForecast() {
        // given
        ForecastClient forecastClient = new ForecastClient();
        // when
        ForecastClientResponse result = forecastClient.getForecast(20, 50, LocalDate.now().plusDays(2));
        // then
        assertThat(result).isNotNull();
    }
}
