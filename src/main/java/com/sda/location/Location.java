package com.sda.location;

import com.sda.forecast.Forecast;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String city;
    private String region;
    @Column(nullable = false)
    private String country;
    private int longitude;
    private int latitude;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @OneToMany(mappedBy = "location", fetch = FetchType.EAGER)
    private List<Forecast> forecasts = new ArrayList<>();

    public void addForecast(Forecast forecast){
        forecast.setLocation(this);
        this.forecasts.add(forecast);
    }
}
