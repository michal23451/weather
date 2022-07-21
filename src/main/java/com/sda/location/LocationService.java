package com.sda.location;

import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.List;

@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    Location create(String city, String region, String country, Integer longitude, Integer latitude) {
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("Należy podać miejscowość!");
        }
        if (country == null || country.isBlank()) {
            throw new IllegalArgumentException("Należy podać kraj!");
        }
        if (longitude < -180 || longitude > 180) {
            throw new IllegalArgumentException("Długość geograficzna musi być z przedziału <-180,180>");
        }
        if (latitude < -90 || latitude > 90) {
            throw new IllegalArgumentException("Szerokość geograficzna musi być z przedziału <-90,90>");
        }

        Location location = new Location();
        location.setCity(city);
        location.setRegion(region);
        location.setCountry(country);
        location.setLongitude(longitude);
        location.setLatitude(latitude);
        location.setCreatedDate(Instant.now());

        Location savedLocation = locationRepository.save(location);

        return savedLocation;
    }

    List<Location> getAll() {
        return locationRepository.findAll();
    }

    Location getById(Long id) {
        if (id < 0) {
            throw new IllegalArgumentException("Id nie może być mniejsze od 0!");
        }
        return locationRepository.findById(id).get();
    }
}
