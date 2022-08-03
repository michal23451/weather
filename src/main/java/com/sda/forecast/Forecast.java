package com.sda.forecast;

import com.sda.location.Location;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "location")
public class Forecast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double temperature;
    private int pressure;
    private int humidity;
    @Column(name = "wind_speed")
    private double windSpeed;
    @Column(name = "wind_direction")
    private int windDirection;
    @ManyToOne
    private Location location;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "forecast_date")
    private LocalDate forecastDate;
}