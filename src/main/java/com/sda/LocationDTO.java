package com.sda;

import lombok.*;


@Getter
@Setter
public class LocationDTO {
    private Long id;
    private String city;
    private String region;
    private String country;
    private Integer longitude;
    private Integer latitude;
}
