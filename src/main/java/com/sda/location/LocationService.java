package com.sda.location;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    Location create(String city, String region, String country, Integer longitude, Integer latitude) {
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("Należy podać miejscowość!");
        }
        if (region.isBlank()) {
            region = null;
        }
        if (country == null || country.isBlank()) {
            throw new IllegalArgumentException("Należy podać kraj!");
        }
        if (longitude == null || longitude < -180 || longitude > 180) {
            throw new IllegalArgumentException("Długość geograficzna musi być z przedziału <-180,180>");
        }
        if (latitude == null || latitude < -90 || latitude > 90) {
            throw new IllegalArgumentException("Szerokość geograficzna musi być z przedziału <-90,90>");
        }


        Location location = new Location();
        location.setCity(city);
        location.setRegion(region);
        location.setCountry(country);
        location.setLongitude(longitude);
        location.setLatitude(latitude);
        location.setCreatedDate(LocalDateTime.now());

        Location savedLocation = locationRepository.save(location);

        return savedLocation;
    }

    List<Location> getAll() {
        return locationRepository.findAll();
    }

    public Optional<Location> getById(Long id) {
        if (id < 0) {
            throw new IllegalArgumentException("Id nie może być mniejsze od 0!");
        }
        return locationRepository.findById(id);
    }
}
