package com.sda;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    //POST: /location
    public String createLocation(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            LocationDTO locationDTO = objectMapper.readValue(json, LocationDTO.class);
            Location location = locationService.create(locationDTO.getCity(), locationDTO.getRegion(), locationDTO.getCountry(), locationDTO.getLongitude(), locationDTO.getLatitude());
            LocationDTO response = mapToLocationDTO(location);
            return objectMapper.writeValueAsString(response);
        } catch (Exception e) {
            return "{\"errorMessage\": \"" + e.getMessage() + "\"}";
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

}
