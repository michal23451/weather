package com.sda;

import com.sda.location.Location;
import com.sda.location.LocationRepository;

import java.util.List;
import java.util.Optional;

public class LocationMockRepository implements LocationRepository {

    @Override
    public Location save(Location location) {
        return null;
    }

    @Override
    public List<Location> findAll() {
        return null;
    }

    @Override
    public Optional<Location> findById(Long id) {
        return null;
    }
}
