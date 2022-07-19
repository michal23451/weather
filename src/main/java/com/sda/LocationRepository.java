package com.sda;

import java.util.List;
import java.util.Optional;

public interface LocationRepository {
    Location save(Location location);

    List<Location> findAll();

    Optional<Location> findById(Long id);
}
