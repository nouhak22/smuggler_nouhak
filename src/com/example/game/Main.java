package com.example.game;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    // Scanner object for user input.
    public static final Scanner scanner = new Scanner(System.in);

    // List containing all cities in the game.
    // EventManager.getCitiesMap().values() is assumed to return a collection of City objects.
    public static List<City> allCities = new ArrayList<>(EventManager.getCitiesMap().values());

    // Merchant object representing the player.
    public static Merchant player;

    // Manages travel functionalities such as determining the current city.
    public static TravelManager travelManager;

    public static void main(String[] args) {
        // Initialize the TravelManager object after allCities is populated.
        travelManager = new TravelManager(allCities);

        // EventManager for handling game events. Initialized with the current city from the travelManager.
        EventManager eventManager = new EventManager(travelManager.getCurrentCity());

        // Infinite loop for the main game menu.
        while (true) {
            // Display the introduction menu to the user.
            Menu.displayIntroMenu();

            // Read the user's choice.
            String choice = scanner.nextLine();

            // Handle the user's choice.
            switch (choice) {
                case "1":
                    startGame();
                    break;
                case "2":
                    System.out.println("Thanks for playing! Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please select a number from 1 to 2.");
            }
        }
    }

    public static void startGame() {
        // Display the start menu and initialize the player object.
        player = Menu.startMenu();

        // Re-initialize the TravelManager with allCities.
        travelManager = new TravelManager(allCities);

        // Print a welcome message with the player's name and starting city.
        System.out.println("Welcome " + player.getName() + "! Your adventure begins in " + travelManager.getCurrentCityName());

        // Start the main game loop using EventManager.
        EventManager.gameLoop(player, travelManager);
    }
}

