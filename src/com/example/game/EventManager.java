package com.example.game;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;


public class EventManager {
    private static Map<String, Item> itemsMap = new HashMap<>();// Static maps to store items and cities.
    private static Map<String, City> citiesMap = new HashMap<>();// Static maps to store items and cities.
    private static City currentCity; // The current city where the event is happening.

    // Static initializer block for initializing game data.
    static {

        itemsMap.put("weed", new Item("weed", 10.0));
        itemsMap.put("cocaine", new Item("cocaine", 60.0));
        itemsMap.put("ketamine", new Item("ketamine", 50.0));
        itemsMap.put("mdma", new Item("mdma", 5.0));
        itemsMap.put("heroin", new Item("heroin", 80.0));

        City montreal = new City("montreal"); // Setting up the city of Montreal and its market conditions.
        montreal.setMaxCapacityForItem("weed", 100000);
        montreal.setMaxCapacityForItem("cocaine", 100000);
        montreal.setMaxCapacityForItem("ketamine", 100000);
        montreal.setMaxCapacityForItem("mdma", 100000);
        montreal.setMaxCapacityForItem("heroin", 100000);
        for (Item item : itemsMap.values()) {
            montreal.initializeInventory(itemsMap);  // This initializes the inventory for the item
            montreal.updatePricesForItem(item);  // This updates the prices based on the inventory size
        }
        citiesMap.put("montreal", montreal);

        City london = new City("london");
        london.setMaxCapacityForItem("weed", 100000);
        london.setMaxCapacityForItem("cocaine", 100000);
        london.setMaxCapacityForItem("ketamine", 100000);
        london.setMaxCapacityForItem("mdma", 100000);
        london.setMaxCapacityForItem("heroin", 100000);
        for(Item item : itemsMap.values()){
            london.initializeInventory(itemsMap);
            london.updatePricesForItem(item);
        }
        citiesMap.put("london", london);

        City tokyo = new City("tokyo");
        tokyo.setMaxCapacityForItem("weed", 100000);
        tokyo.setMaxCapacityForItem("cocaine", 100000);
        tokyo.setMaxCapacityForItem("ketamine", 100000);
        tokyo.setMaxCapacityForItem("mdma", 100000);
        tokyo.setMaxCapacityForItem("heroin", 100000);
        for(Item item : itemsMap.values()){
            tokyo.initializeInventory(itemsMap);
            tokyo.updatePricesForItem(item);
        }
        citiesMap.put("tokyo", tokyo);
    }

    // Constructor for the EventManager.
    public EventManager(City startingCity) {
        currentCity = startingCity;
    }

    // Fetch an item by its name from the items map.
    public static Item getItemByName(String itemName) {
        return itemsMap.get(itemName.toLowerCase());
    }

    // Fetch a city by its name from the cities map.
    public static City getCityByName(String cityName) {
        return citiesMap.get(cityName.toLowerCase());
    }

    // Getter for citiesMap.
    public static Map<String, City> getCitiesMap() {
        return citiesMap;
    }

    /**
   * Attempts to buy an item for the merchant in the current city.
   * It checks if the merchant has enough money and capacity before making the purchase.*/
    public static boolean tryBuyItem(Merchant merchant, Item item, int quantity) {
        Double totalCost = currentCity.getBuyPrice(item) * quantity;
        if(merchant.getMoney() >= totalCost && merchant.canAddItem(item, quantity)) {
            merchant.purchaseItem(item, quantity, totalCost);
            currentCity.updatePricesForItem(item);  // Update prices after transaction
            return true;
        }
        return false;
    }
   /**
   * Attempts to sell an item for the merchant in the current city.
   * It checks if the merchant has the specified quantity of the item before selling.*/
   public static boolean trySellItem(Merchant merchant, Item item, int quantity) {
       Double totalRevenue = currentCity.getSellPrice(item) * quantity;
       if(merchant.canSellItem(item, quantity)) {
           merchant.sellItem(item, quantity, totalRevenue);
           currentCity.updatePricesForItem(item);  // Update prices after transaction
           return true;
       }
       return false;
   }
    public static void gameLoop(Merchant player, TravelManager travelManager) {
        City currentCity = travelManager.getCurrentCity();
        Scanner scanner = new Scanner(System.in);
        boolean continuePlaying = true;

        while (continuePlaying) {
            String action = Menu.merchantMenu(player, currentCity);
            if (action == null) {
                System.out.println("Error: Unexpected input or error in merchantMenu.");
                continue;  // This will go to the next iteration of the loop.
            }

            switch (action) {
                case "1":
                    Menu.sellItem(player, currentCity, itemsMap, scanner); // Sell items in the current city.
                    break;
                case "2":
                    Menu.buyItem(player, currentCity, itemsMap, scanner); // Buy items in the current city.
                    break;
                case "3":
                    travelManager.attemptToTravel(player, scanner);   // Attempt to travel to another city.
                    currentCity = travelManager.getCurrentCity();
                    break;
                case "4":
                    System.out.println("Thanks for playing! Exiting..."); // Exit the game.
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please select a number from 1 to 4.");
            }
        }
    }
}
