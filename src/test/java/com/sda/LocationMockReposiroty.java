package com.sda;

import java.util.List;
import java.util.Optional;

public class LocationMockReposiroty implements LocationRepository{
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
