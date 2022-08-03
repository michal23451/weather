package com.sda;

import com.sda.forecast.ForecastController;
import com.sda.location.LocationController;
import lombok.RequiredArgsConstructor;

import java.util.InputMismatchException;
import java.util.Scanner;

@RequiredArgsConstructor
public class UserInterface {

    private final Scanner scanner;
    private final LocationController locationController;
    private final ForecastController forecastController;

    public void run() {
        System.out.println("Aplikacja jest uruchomiona.");
        int option;
        do {
            System.out.println("\nWitaj w aplikacji pogodowej. Co chcesz zrobić?");
            System.out.println("1. Dodać lokalizację.");
            System.out.println("2. Wyświetl wszystkie lokalizacje.");
            System.out.println("3. Pobierz dane pogodowe dla lokalizacji o podanym id.");
            System.out.println("0. Zamknąć aplikację.");

            //option = scanner.nextInt();
            option = checkIntFromScanner();
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
                default:
                    System.out.println("Niepoprawny wybór.");
                    break;
                case 0:
                    return;
            }
        } while (option != 0);
    }

    private void createLocation(Scanner scanner) {
        System.out.print("Podaj miejscowość: ");
        String city = scanner.nextLine();
        System.out.print("Podaj region: ");
        String region = scanner.nextLine();
        System.out.print("Podaj kraj: ");
        String country = scanner.nextLine();
        System.out.print("Podaj długość geograficzną: ");
        int longitude = checkIntFromScanner();
        System.out.print("Podaj szerokość geograficzną: ");
        int latitude = checkIntFromScanner();
        String httpRequest = String.format("{\"city\":\"%s\",\"region\":\"%s\",\"country\":\"%s\",\"longitude\":%d,\"latitude\":%d}", city, region, country, longitude, latitude);
        System.out.println("Wysyłam HTTP request: " + httpRequest);
        String httpResponse = locationController.createLocation(httpRequest);
        System.out.println("Odpowiedź z serwera:  " + httpResponse);
    }

    private void allLocation(Scanner scanner) {
        System.out.println("Wszystkie lokalizacje:");
        String httpResponse = locationController.getAllLocations();
        System.out.println("Odpowiedź z serwera:  " + httpResponse);
    }

    private void forecastById(Scanner scanner) {
        System.out.print("Podaj id lokalizacji: ");
        long id = checkLongFromScanner();
        System.out.print("Podaj dzień, dla którego chcesz wyświetlić prognozę [1- jutro itd., max 5]: ");
        int day = checkIntFromScanner();
        String httpResponse = forecastController.getForecast(id, day);
        System.out.println("Odpowiedź z serwera: " + httpResponse);
    }

    private int checkIntFromScanner() {
        while (true) {
            try {
                int input = scanner.nextInt();
                return input;
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.print("Podana wartość musi być cyfrą! Podaj cyfrę: ");
            }
        }
    }

    private long checkLongFromScanner() {
        while (true) {
            try {
                long input = scanner.nextLong();
                return input;
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.print("Podana wartość musi być cyfrą! Podaj cyfrę: ");
            }
        }
    }

}


