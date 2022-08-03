package com.sda.location;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;
    private final ObjectMapper objectMapper;
    private final LocationMapper locationMapper;

    //POST: /location
    public String createLocation(String json) {
        try {
            LocationDTO locationDTO = objectMapper.readValue(json, LocationDTO.class);
            Location location = locationService.create(locationDTO.getCity(), locationDTO.getRegion(), locationDTO.getCountry(), locationDTO.getLongitude(), locationDTO.getLatitude());
            LocationDTO response = locationMapper.mapLocationToLocationDTO(location);
            return objectMapper.writeValueAsString(response);
        } catch (Exception e) {
            return "{\"errorMessage\":\"" + e.getMessage() + "\"}";
        }
    }



    //GET: /locations
    public String getAllLocations() {
        try {
            List<Location> locations = locationService.getAll();
            List<LocationDTO> locationsDTO = locations.stream()
                    .map(location -> locationMapper.mapLocationToLocationDTO(location))
                    .collect(Collectors.toList());
            return objectMapper.writeValueAsString(locationsDTO);
        } catch (Exception e) {
            return "{\"errorMessage\": \"" + e.getMessage() + "\"}";
        }
    }

}
