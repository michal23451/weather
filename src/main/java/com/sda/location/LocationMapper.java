package com.sda.location;

public class LocationMapper {
    LocationDTO mapLocationToLocationDTO(Location location) {
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
