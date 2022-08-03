package com.sda;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.forecast.*;
import com.sda.forecast.client.ForecastClient;
import com.sda.location.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;

import java.util.Scanner;

@Slf4j
public class Application {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        WindUnitMapper windUnitMapper = new WindUnitMapper();
        ForecastMapper forecastMapper = new ForecastMapper(windUnitMapper);
        LocationMapper locationMapper = new LocationMapper();

        LocationRepository locationRepository = new LocationRepositoryImpl(sessionFactory);
        LocationService locationService = new LocationService(locationRepository);
        LocationController locationController = new LocationController(locationService, objectMapper, locationMapper);

        ForecastClient forecastClient = new ForecastClient(objectMapper);
        ForecastRepository forecastRepository = new ForecastRepositoryImpl(sessionFactory);
        ForecastService forecastService = new ForecastService(forecastRepository, locationService, forecastClient, forecastMapper);
        ForecastController forecastController = new ForecastController(forecastService, objectMapper, forecastMapper);

        UserInterface userInterface = new UserInterface(scanner, locationController, forecastController);
        userInterface.run();


        scanner.close();
        sessionFactory.close();
    }
}



