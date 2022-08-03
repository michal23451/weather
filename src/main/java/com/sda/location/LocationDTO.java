package com.sda.location;

import lombok.*;


@Getter
@Setter
public class LocationDTO {
    private long id;
    private String city;
    private String region;
    private String country;
    private int longitude;
    private int latitude;

}
