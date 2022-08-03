package com.sda.forecast;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class WindUnitMapper {

    private final String[] DIRECTIONS = {"N", "NNE", "NE", "ENE", "E", "ESE", "SE", "SSE", "S", "SSW", "SW", "WSW", "W", "WNW", "NW", "NNW"};

    String mapWindDegreeToDirection(int degree) {
        if (degree < 0 || degree > 360) {
            throw new IllegalArgumentException("Kierunek wiatru musi znajdować się w przedziale <0, 360>");
        }
        int i = (int) ((degree / 22.5) + 0.5);
        return DIRECTIONS[i % 16];
    }
}
