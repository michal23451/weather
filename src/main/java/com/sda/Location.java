package com.sda;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String city;
    private String region;
    @Column(nullable = false)
    private String country;
    private Integer longitude;
    private Integer latitude;
    @Column(name = "created_date")
    private Instant createdDate;
}
