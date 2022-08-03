package com.sda.location;

import com.sda.forecast.Forecast;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Getter
public class LocationRepositoryMock implements LocationRepository {

    private final List<Location> locations;

    private List<Forecast> generateForecastsList() {
        Forecast forecast1 = new Forecast();
        forecast1.setId(1L);
        forecast1.setTemperature(11);
        forecast1.setPressure(12);
        forecast1.setHumidity(13);
        forecast1.setWindSpeed(14);
        forecast1.setWindDirection(15);
        forecast1.setCreatedDate(LocalDateTime.parse("2022-04-04T00:00:00"));
        forecast1.setForecastDate(LocalDate.parse("2022-04-05"));

        Forecast forecast2 = new Forecast();
        forecast2.setId(2L);
        forecast2.setTemperature(21);
        forecast2.setPressure(22);
        forecast2.setHumidity(23);
        forecast2.setWindSpeed(24);
        forecast2.setWindDirection(25);
        forecast2.setCreatedDate(LocalDateTime.parse("2022-05-05T00:00:00"));
        forecast2.setForecastDate(LocalDate.parse("2022-05-06"));

        Forecast forecast3 = new Forecast();
        forecast3.setId(3L);
        forecast3.setTemperature(31);
        forecast3.setPressure(32);
        forecast3.setHumidity(33);
        forecast3.setWindSpeed(34);
        forecast3.setWindDirection(35);
        forecast3.setCreatedDate(LocalDateTime.parse("2022-06-06T00:00:00"));
        forecast3.setForecastDate(LocalDate.parse("2022-06-07"));

        return List.of(forecast1, forecast2, forecast3);
    }


    public LocationRepositoryMock() {
        List<Forecast> forecasts = generateForecastsList();

        Location location1 = new Location();
        location1.setId(1L);
        location1.setCity("city1");
        location1.setRegion("region1");
        location1.setCountry("country1");
        location1.setLongitude(11);
        location1.setLatitude(12);
        location1.setCreatedDate(LocalDateTime.parse("2022-01-01T00:00:00"));
        location1.setForecasts(List.of(forecasts.get(0), forecasts.get(1)));

        Location location2 = new Location();
        location2.setId(2L);
        location2.setCity("city2");
        location2.setRegion("region2");
        location2.setCountry("country2");
        location2.setLongitude(21);
        location2.setLatitude(22);
        location2.setCreatedDate(LocalDateTime.parse("2022-02-02T00:00:00"));
        location2.setForecasts(null);

        Location location3 = new Location();
        location3.setId(3L);
        location3.setCity("city3");
        location3.setRegion("region3");
        location3.setCountry("country3");
        location3.setLongitude(31);
        location3.setLatitude(32);
        location3.setCreatedDate(LocalDateTime.parse("2022-03-03T00:00:00"));
        location3.setForecasts(List.of(forecasts.get(2)));

        this.locations = List.of(location1, location2, location3);
    }

    @Override
    public Location save(Location location) {
        location.setId(100L);
        return location;
    }

    @Override
    public List<Location> findAll() {
        return locations;
    }

    @Override
    public Optional<Location> findById(long id) {
        return locations.stream()
                .filter(l -> l.getId() == id)
                .findFirst();
    }
}
