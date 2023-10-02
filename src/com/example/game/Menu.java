package com.example.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Map;

import static com.example.game.EventManager.getItemByName;

public class Menu {
    public static List<City> allCities = new ArrayList<>(EventManager.getCitiesMap().values());
    private static Scanner scanner = new Scanner(System.in);
    public static void displayIntroMenu() {
        System.out.println("░██████╗███╗░░░███╗██╗░░░██╗░██████╗░░██████╗░██╗░░░░░███████╗██████╗░  ░██████╗░░█████╗░███╗░░░███╗███████╗");
        System.out.println("██╔════╝████╗░████║██║░░░██║██╔════╝░██╔════╝░██║░░░░░██╔════╝██╔══██╗  ██╔════╝░██╔══██╗████╗░████║██╔════╝");
        System.out.println("╚█████╗░██╔████╔██║██║░░░██║██║░░██╗░██║░░██╗░██║░░░░░█████╗░░██████╔╝  ██║░░██╗░███████║██╔████╔██║█████╗░░");
        System.out.println("░╚═══██╗██║╚██╔╝██║██║░░░██║██║░░╚██╗██║░░╚██╗██║░░░░░██╔══╝░░██╔══██╗  ██║░░╚██╗██╔══██║██║╚██╔╝██║██╔══╝░░");
        System.out.println("██████╔╝██║░╚═╝░██║╚██████╔╝╚██████╔╝╚██████╔╝███████╗███████╗██║░░██║  ╚██████╔╝██║░░██║██║░╚═╝░██║███████╗");
        System.out.println("╚═════╝░╚═╝░░░░░╚═╝░╚═════╝░░╚═════╝░░╚═════╝░╚══════╝╚══════╝╚═╝░░╚═╝  ░╚═════╝░╚═╝░░╚═╝╚═╝░░░░░╚═╝╚══════╝");
        System.out.println();
        System.out.println("[1] Start Game");
        System.out.println("[2] Exit");
        System.out.println("_________________________________________________________");
        System.out.print("Enter your choice: ");
    }
    public static Merchant startMenu() {
        Scanner scanner = new Scanner(System.in);  // Initialize the Scanner

        // Print a decorative top border
        System.out.println("░██╗░░░░░░░██╗███████╗██╗░░░░░░█████╗░░█████╗░███╗░░░███╗███████╗");
        System.out.println("░██║░░██╗░░██║██╔════╝██║░░░░░██╔══██╗██╔══██╗████╗░████║██╔════╝");
        System.out.println("░╚██╗████╗██╔╝█████╗░░██║░░░░░██║░░╚═╝██║░░██║██╔████╔██║█████╗░");
        System.out.println("░░████╔═████║░██╔══╝░░██║░░░░░██║░░██╗██║░░██║██║╚██╔╝██║██╔══╝░░");
        System.out.println("░░╚██╔╝░╚██╔╝░███████╗███████╗╚█████╔╝╚█████╔╝██║░╚═╝░██║███████╗");
        System.out.println("░░╚═╝░░░╚═╝░░╚══════╝╚══════╝░╚════╝░░╚════╝░╚═╝░░░░░╚═╝╚══════╝");

        // Give some spacing for clarity
        System.out.println();

        System.out.print("Please enter your name: ");
        String userName = scanner.nextLine();

        Merchant player = new Merchant(1000000, userName);  // Create a new Merchant with the entered name and starting money

        System.out.println();
        System.out.println("Welcome, " + userName + "! Get ready to become the next Don Pablo!");
        System.out.println();

        // Prompt user to press any key to continue
        System.out.println("Press Enter key to continue...");
        scanner.nextLine();  // Wait for the user to press Enter

        return player;
    }
    public static String merchantMenu(Merchant player, City currentCity) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("─────────────────────────────────────");
        System.out.println("███╗░░░███╗███████╗███╗░░██╗██╗░░░██╗");
        System.out.println("████╗░████║██╔════╝████╗░██║██║░░░██║");
        System.out.println("██╔████╔██║█████╗░░██╔██╗██║██║░░░██║");
        System.out.println("██║╚██╔╝██║██╔══╝░░██║╚████║██║░░░██║");
        System.out.println("██║░╚═╝░██║███████╗██║░╚███║╚██████╔╝");
        System.out.println("╚═╝░░░░░╚═╝╚══════╝╚═╝░░╚══╝░╚═════╝░");
        System.out.println("─────────────────────────────────────");
        System.out.println();
        System.out.println("Current City: " + currentCity.getName());
        System.out.println("Money: " + player.getMoney());

        // Print the inventory details
        System.out.println("─────────────── Inventory ───────────────");
        System.out.printf("%-15s | %10s%n", "Item", "Quantity");  // Header
        System.out.println("─────────────────────────────────────────");  // Separator
        for (Map.Entry<Item, Integer> entry : player.getInventory().entrySet()) {
            System.out.printf("%-15s | %10d%n", entry.getKey().getName(), entry.getValue());
        }
        System.out.println("─────────────────────────────────────────");  // Ending separator

        double fillPercentage = player.getFillPercentage();
        int chanceOfGettingCaught = (int) (fillPercentage * 100); // Convert to a percentage out of 100
        System.out.println("Chance of getting caught: " + chanceOfGettingCaught + "%");

        System.out.println("────────────────────────────────────────────────────────────────────────");
        System.out.println("[1] Sell items");
        System.out.println("[2] buy items");
        System.out.println("[3] change city");
        System.out.println("[4] Exit game");
        String choice = scanner.nextLine();
        return choice;
    }
    public static void sellItem(Merchant player, City currentCity, Map<String, Item> itemsMap, Scanner scanner) {

        System.out.println("───────────────────────────────────────────");
        System.out.println("         ITEMS TO SELL IN " + currentCity.getName().toUpperCase() + "           ");
        System.out.println("───────────────────────────────────────────");
        System.out.println("ITEM NAME       PRICE       CITY INVENTORY ");
        System.out.println("───────────────────────────────────────────");

        for (String itemName : itemsMap.keySet()) {
            Item item = itemsMap.get(itemName);
            double sellPrice = currentCity.getSellPrice(item);
            int cityInventoryForItem = currentCity.getItemQuantity(item);
            System.out.printf("%-15s $%-10.2f %d%n", itemName, sellPrice, cityInventoryForItem);
        }

        System.out.println("───────────────────────────────────────────");
        System.out.print("Which item would you like to sell? ");

        String itemToSell = scanner.nextLine().trim();

        if (!itemsMap.containsKey(itemToSell)) {
            System.out.println("Invalid item name.");
            return;
        }

        System.out.println("Money: " + player.getMoney());

        // Print the inventory details
        System.out.println("─────────────── Inventory ───────────────");
        System.out.printf("%-15s | %10s%n", "Item", "Quantity");  // Header
        System.out.println("─────────────────────────────────────────");  // Separator
        for (Map.Entry<Item, Integer> entry : player.getInventory().entrySet()) {
            System.out.printf("%-15s | %10d%n", entry.getKey().getName(), entry.getValue());
        }
        System.out.println("─────────────────────────────────────────");  // Ending separator

        System.out.print("How many would you like to sell? ");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid quantity. Please enter a positive number.");
            scanner.next(); // Clear the invalid input
        }

        int quantityToSell = scanner.nextInt();
        scanner.nextLine();

        Item itemToCheck = EventManager.getItemByName(itemToSell);
        if (quantityToSell <= 0 || player.getItemQuantity(itemToCheck) < quantityToSell) {
            System.out.println("Invalid quantity. Please ensure you have enough items to sell.");
            return; // Exit from the method early
        }

        Item sellItem = getItemByName(itemToSell);
        boolean result = EventManager.trySellItem(player, sellItem, quantityToSell);
        if (result) {
            double totalRevenue = quantityToSell * currentCity.getSellPrice(sellItem);
            System.out.printf("You sold %d units of %s for $%.2f%n", quantityToSell, itemToSell, totalRevenue);
        } else {
            System.out.println("Transaction failed. Check inventory and city stocks.");
        }
        System.out.println("───────────────────────────────────────────"); // End separator
    }

    public static void buyItem(Merchant player, City currentCity, Map<String, Item> itemsMap, Scanner scanner) {

        System.out.println("───────────────────────────────────────────");
        System.out.println("         ITEMS TO BUY IN " + currentCity.getName().toUpperCase() + "            ");
        System.out.println("───────────────────────────────────────────");
        System.out.println("ITEM NAME       PRICE       CITY INVENTORY ");
        System.out.println("───────────────────────────────────────────");

        for (String itemName : itemsMap.keySet()) {
            Item item = itemsMap.get(itemName);
            double buyPrice = currentCity.getBuyPrice(item);
            int cityInventoryForItem = currentCity.getItemQuantity(item);
            System.out.printf("%-15s $%-10.2f %d%n", itemName, buyPrice, cityInventoryForItem);
        }

        System.out.println("Money: " + player.getMoney());

        // Print the inventory details
        System.out.println("─────────────── Inventory ───────────────");
        System.out.printf("%-15s | %10s%n", "Item", "Quantity");  // Header
        System.out.println("─────────────────────────────────────────");  // Separator
        for (Map.Entry<Item, Integer> entry : player.getInventory().entrySet()) {
            System.out.printf("%-15s | %10d%n", entry.getKey().getName(), entry.getValue());
        }
        System.out.println("─────────────────────────────────────────");  // Ending separator

        System.out.print("Which item would you like to buy? ");

        String itemToBuy = scanner.nextLine().trim();

        if (!itemsMap.containsKey(itemToBuy)) {
            System.out.println("Invalid item name.");
            return; // Exit early
        }

        System.out.print("How many would you like to buy? ");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid quantity. Please enter a positive number.");
            scanner.next(); // Clear the invalid input
            return; // Exit early
        }

        int quantityToBuy = scanner.nextInt();
        scanner.nextLine();

        if (quantityToBuy <= 0 || currentCity.getItemQuantity(itemsMap.get(itemToBuy)) < quantityToBuy) {
            System.out.println("Invalid quantity. The city doesn't have enough stock for that item.");
            return; // Exit early
        }

        Item buyItem = EventManager.getItemByName(itemToBuy);

        double totalCost = currentCity.getBuyPrice(buyItem) * quantityToBuy;

        if (player.getMoney() < totalCost) {
            System.out.println("You don't have enough money for this transaction.");
            return; // Exit early
        }

        boolean result = EventManager.tryBuyItem(player, buyItem, quantityToBuy);
        if (result) {
            System.out.printf("You bought %d units of %s for $%.2f%n", quantityToBuy, itemToBuy, totalCost);
        } else {
            System.out.println("Transaction failed. Check your funds and inventory space.");
        }

        System.out.println("───────────────────────────────────────────"); // End separator
    }


    public static void travel(TravelManager travelManager, List<City> allCities, Scanner scanner) {

        System.out.println("Which city would you like to travel to? ");
        System.out.println("montreal");
        System.out.println("tokyo");
        System.out.println("london");
        String destinationCityName = scanner.nextLine();

        City destination = getCityByName(destinationCityName, allCities); // Assuming the method now requires the list of cities
        if (destination == null) {
            System.out.println("Invalid city name.");
            return; // Exit early
        }

        travelManager.travelTo(destination); // This line might introduce side effects

    }

    // Sample adjusted method
    private static City getCityByName(String cityName, List<City> allCities) {
        for (City city : allCities) {
            if (city.getName().equalsIgnoreCase(cityName)) {
                return city;
            }
        }
        return null;
    }

}


