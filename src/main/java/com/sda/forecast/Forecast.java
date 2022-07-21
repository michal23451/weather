package com.sda.forecast;

import com.sda.location.Location;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Forecast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float temperature;
    private int pressure;
    private int humidity;
    @Column(name = "wind_speed")
    private int windSpeed;
    @Column(name = "wind_direction")
    private int windDirection;
    @ManyToOne
    private Location location;
    private Instant createdDate;
    private Instant forecastDate;
}