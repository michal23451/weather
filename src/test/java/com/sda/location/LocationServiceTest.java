package com.sda.location;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class LocationServiceTest {

    LocationService locationService;

    @BeforeEach
    void setup() {
        LocationRepositoryMock locationRepository = new LocationRepositoryMock();
        locationService = new LocationService(locationRepository);
    }


    @Test
    void createLocation_whenDataIsCorrect_returnsCorrectLocationObject() {
        //given
        Location location;
        //when
        location = locationService.create("city1", "region1", "country1", 11, 12);
        //then
        assertThat(location.getId()).isGreaterThan(0L);
        assertThat(location.getId()).isEqualTo(100L);
        assertThat(location.getCity()).isEqualTo("city1");
        assertThat(location.getRegion()).isEqualTo("region1");
        assertThat(location.getCountry()).isEqualTo("country1");
        assertThat(location.getLongitude()).isEqualTo(11);
        assertThat(location.getLatitude()).isEqualTo(12);
        assertThat(location.getCreatedDate()).isNotNull();
    }

    @Test
    void createLocation_whenCityIsNull_throwsException() {
        //given
        Throwable throwable;
        //when
        throwable = catchThrowable(() -> locationService.create(null, "region1", "country1", 11, 12));
        //then
        assertThat(throwable).isNotNull();
        //assertThat(throwable.getMessage()).contains("miejscowość");
        assertThat(throwable.getMessage()).isEqualTo("Należy podać miejscowość!");
        assertThat(throwable).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void createLocation_whenCityIsBlank_throwsException() {
        //given
        Throwable throwable;
        //when
        throwable = catchThrowable(() -> locationService.create("     ", "region1", "country1", 11, 12));
        //then
        assertThat(throwable).isNotNull();
        //assertThat(throwable.getMessage()).contains("miejscowość");
        assertThat(throwable.getMessage()).isEqualTo("Należy podać miejscowość!");
        assertThat(throwable).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void createLocation_whenRegionIsBlank_returnCorrectLocationObjectWithNullRegion() {
        //given
        Location location;
        //when
        location = locationService.create("city1", "   ", "country1", 11, 12);
        //then
        assertThat(location.getId()).isGreaterThan(0L);
        assertThat(location.getId()).isEqualTo(100L);
        assertThat(location.getCity()).isEqualTo("city1");
        assertThat(location.getRegion()).isNull();
        assertThat(location.getCountry()).isEqualTo("country1");
        assertThat(location.getLongitude()).isEqualTo(11);
        assertThat(location.getLatitude()).isEqualTo(12);
        assertThat(location.getCreatedDate()).isNotNull();
    }

    @Test
    void createLocation_whenCountryIsNull_throwsException() {
        //given
        Throwable throwable;
        //when
        throwable = catchThrowable(() -> locationService.create("city1", "region1", null, 11, 12));
        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable.getMessage()).isEqualTo("Należy podać kraj!");
        assertThat(throwable).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void createLocation_whenCountryIsBlank_throwsException() {
        //given
        Throwable throwable;
        //when
        throwable = catchThrowable(() -> locationService.create("city1", "region1", "   ", 11, 12));
        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable.getMessage()).isEqualTo("Należy podać kraj!");
        assertThat(throwable).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void createLocation_whenLongitudeIsNull_throwsException() {
        //given
        Throwable throwable;
        //when
        throwable = catchThrowable(() -> locationService.create("city1", "region1", "country1", null, 12));
        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable.getMessage()).isEqualTo("Długość geograficzna musi być z przedziału <-180,180>");
        assertThat(throwable).isExactlyInstanceOf(IllegalArgumentException.class);

    }

    @Test
    void createLocation_whenLongitudeIsBiggerThanMaximum_throwsException() {
        //given
        Throwable throwable;
        //when
        throwable = catchThrowable(() -> locationService.create("city1", "region1", "country1", 181, 12));
        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable.getMessage()).isEqualTo("Długość geograficzna musi być z przedziału <-180,180>");
        assertThat(throwable).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void createLocation_whenLongitudeIsSmallerThanMinimum_throwsException() {
        //given
        Throwable throwable;
        //when
        throwable = catchThrowable(() -> locationService.create("city1", "region1", "country1", -181, 12));
        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable.getMessage()).isEqualTo("Długość geograficzna musi być z przedziału <-180,180>");
        assertThat(throwable).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void createLocation_whenLatitudeIsNull_throwsException() {
        //given
        Throwable throwable;
        //when
        throwable = catchThrowable(() -> locationService.create("city1", "region1", "country1", 11, null));
        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable.getMessage()).isEqualTo("Szerokość geograficzna musi być z przedziału <-90,90>");
        assertThat(throwable).isExactlyInstanceOf(IllegalArgumentException.class);

    }

    @Test
    void createLocation_whenLatitudeIsBiggerThanMaximum_throwsException() {
        //given
        Throwable throwable;
        //when
        throwable = catchThrowable(() -> locationService.create("city1", "region1", "country1", 11, 91));
        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable.getMessage()).isEqualTo("Szerokość geograficzna musi być z przedziału <-90,90>");
        assertThat(throwable).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void createLocation_whenLatitudeIsSmallerThanMinimum_throwsException() {
        //given
        Throwable throwable;
        //when
        throwable = catchThrowable(() -> locationService.create("city1", "region1", "country1", 11, -91));
        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable.getMessage()).isEqualTo("Szerokość geograficzna musi być z przedziału <-90,90>");
        assertThat(throwable).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void createLocation_whenLongitudeAndLatitudeAreMaximumValidValues_returnsCorrectLocationObjects() {
        //given
        Location location;
        //when
        location = locationService.create("city1","region1", "country1", 180, 90);
        //then
        assertThat(location).isNotNull();
        assertThat(location.getId()).isGreaterThan(0L);
        assertThat(location.getId()).isEqualTo(100L);
        assertThat(location.getCity()).isEqualTo("city1");
        assertThat(location.getRegion()).isEqualTo("region1");
        assertThat(location.getCountry()).isEqualTo("country1");
        assertThat(location.getLongitude()).isEqualTo(180);
        assertThat(location.getLatitude()).isEqualTo(90);
        assertThat(location.getCreatedDate()).isNotNull();
    }

    @Test
    void createLocation_whenLongitudeAndLatitudeAreMinimumValidValues_returnsCorrectLocationObjects() {
        //given
        Location location;
        //when
        location = locationService.create("city1","region1", "country1", -180, -90);
        //then
        assertThat(location).isNotNull();
        assertThat(location.getId()).isGreaterThan(0L);
        assertThat(location.getId()).isEqualTo(100L);
        assertThat(location.getCity()).isEqualTo("city1");
        assertThat(location.getRegion()).isEqualTo("region1");
        assertThat(location.getCountry()).isEqualTo("country1");
        assertThat(location.getLongitude()).isEqualTo(-180);
        assertThat(location.getLatitude()).isEqualTo(-90);
        assertThat(location.getCreatedDate()).isNotNull();
    }

    @Test
    void getAllLocations_whenDatabaseIsNotEmpty_returnsLocationsList() {
        //given
        List<Location> locations;
        //when
        locations = locationService.getAll();
        //then
        assertThat(locations).isNotNull();
        assertThat(locations).hasSizeGreaterThan(0);
        assertThat(locations).hasSize(3);
    }

    @Test
    void getLocationById_whenDatabaseHasResult_returnsLocation() {
        //given
        Location location;
        //when
        location = locationService.getById(2L).get();
        //then
        assertThat(location).isNotNull();
        assertThat(location.getId()).isEqualTo(2L);
        assertThat(location.getCity()).isEqualTo("city2");
    }

    @Test
    void getLocationById_whenDatabaseHasNoResult_returnsEmptyOptional() {
        //given
        Optional<Location> locationOptional;
        //when
        locationOptional = locationService.getById(5L);
        //then
        assertThat(locationOptional).isNotNull();
        assertThat(locationOptional).isEmpty();
    }

    @Test
    void getLocationById_whenIdIsLessThanZero_throwsException() {
        //given
        Throwable throwable;
        //when
        throwable = catchThrowable(() -> locationService.getById(-1L).get());
        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable.getMessage()).isEqualTo("Id nie może być mniejsze od 0!");
        assertThat(throwable).isExactlyInstanceOf(IllegalArgumentException.class);
    }




}