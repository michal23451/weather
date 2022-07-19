package com.sda;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;
    private final ObjectMapper objectMapper;

    //POST: /location
    public String createLocation(String json) {
        try {
            LocationDTO locationDTO = objectMapper.readValue(json, LocationDTO.class);
            Location location = locationService.create(locationDTO.getCity(), locationDTO.getRegion(), locationDTO.getCountry(), locationDTO.getLongitude(), locationDTO.getLatitude());
            LocationDTO response = mapToLocationDTO(location);
            return objectMapper.writeValueAsString(response);
        } catch (Exception e) {
            return "{\"errorMessage\":\"" + e.getMessage() + "\"}";
        }
    }

    private LocationDTO mapToLocationDTO(Location location) {
        LocationDTO response = new LocationDTO();
        response.setId(location.getId());
        response.setCity(location.getCity());
        response.setRegion(location.getRegion());
        response.setCountry(location.getCountry());
        response.setLongitude(location.getLongitude());
        response.setLatitude(location.getLatitude());
        return response;
    }

    //GET: /locations
    public String getLocations() {
        try {
            List<Location> locations = locationService.getAll();
            List<LocationDTO> locationsDTO = locations.stream()
                    .map(location -> mapToLocationDTO(location))
                    .collect(Collectors.toList());
            return objectMapper.writeValueAsString(locationsDTO);
        } catch (Exception e) {
            return "{\"errorMessage\": \"" + e.getMessage() + "\"}";
        }
    }

    //POST /location_by_id
    public String getLocationById(String json) {
        try {
            LocationDTO locationDTO = objectMapper.readValue(json, LocationDTO.class);
            Location location = locationService.getById(locationDTO.getId());
            LocationDTO response = mapToLocationDTO(location);
            return objectMapper.writeValueAsString(response);
        } catch (Exception e) {
            return "{\"errorMessage\": \"" + e.getMessage() + "\"}";
        }
    }

}
