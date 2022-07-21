package com.sda;

import com.sda.forecast.ForecastClient;
import com.sda.location.LocationController;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Scanner;

@RequiredArgsConstructor
public class UserInterface {

    private final LocationController locationController;

    public void run() {
        System.out.println("Aplikacja jest uruchomiona\n");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nWitaj w aplikacji pogodowej. Co chcesz zrobić?");
            System.out.println("1. Dodać lokalizację.");
            System.out.println("2. Wyświetl wszystkie lokalizacje.");
            System.out.println("3. Pobierz dane pogodowe dla lokalizacji o podanym id.");
            //System.out.println("9. TESTOWO - Wyświetl lokalizację o zadanym id.");
            System.out.println("0. Zamknąć aplikację.");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    createLocation(scanner);
                    break;
                case 2:
                    allLocation(scanner);
                    break;
                case 3:
                    forecastById(scanner);
                    break;
                case 9:
                    locationById(scanner);
                    break;
                case 0:
                    scanner.close();
                    return;
            }
        }
    }

    public void createLocation(Scanner scanner) {
        System.out.print("Podaj miejscowość: ");
        String city = scanner.nextLine();
        System.out.print("Podaj region: ");
        String region = scanner.nextLine();
        System.out.print("Podaj kraj: ");
        String country = scanner.nextLine();
        System.out.print("Podaj długość geograficzną: ");
        Integer longitude = scanner.nextInt();
        System.out.print("Podaj szerokość geograficzną: ");
        Integer latitude = scanner.nextInt();
        String httpRequest = String.format("{\"city\":\"%s\",\"region\":\"%s\",\"country\":\"%s\",\"longitude\":%d,\"latitude\":%d}", city, region, country, longitude, latitude);
        System.out.println("Wysyłam HTTP request: " + httpRequest);
        String httpResponse = locationController.createLocation(httpRequest);
        System.out.println("Odpowiedź z serwera:  " + httpResponse);
    }

    public void allLocation(Scanner scanner) {
        System.out.println("Wszystkie lokalizacje:");
        System.out.println("Wysyłam HTTP request: {\"location\":\"all\"}");
        String httpResponse = locationController.getLocations();
        System.out.println("Odpowiedź z serwera:  " + httpResponse);
    }

    public void locationById(Scanner scanner) {
        System.out.print("Podaj id lokalizacji: ");
        Integer id = scanner.nextInt();
        String httpRequest = String.format("{\"id\":\"%d\"}", id);
        System.out.println("Wysyłam HTTP request: " + httpRequest);
        String httpResponse = locationController.getLocationById(httpRequest);
        System.out.println("Odpowiedź z serwera:  " + httpResponse);
    }

    public void forecastById(Scanner scanner) {
        System.out.print("Podaj id lokalizacji: ");
        Integer id = scanner.nextInt();
        System.out.print("Podaj dzień, dla którego chcesz wyświetlić prognozę [0-dzisiaj, 1- jutro itd.]: ");
        Integer day = scanner.nextInt();
        ForecastClient externalForacastClient = new ForecastClient();
        System.out.println(externalForacastClient.getForecast(50,19, LocalDate.parse("2022-07-20")));
    }
}
