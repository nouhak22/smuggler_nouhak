package com.example.game;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class City {
    private final String cityName;
    private final Map<String, Integer> maxItemCapacities; // Max allowable quantities for each item in the city
    private final Map<String, Integer> cityItemQuantities; // Current quantities of specific items in the city.
    private final Map<String, Double> buyPrices; // Prices the city offers to buy specific items.
    private final Map<String, Double> sellPrices; // Prices at which the city sells specific items.
    private static final Random RAND = new Random();

    // Constructor
    public City(String cityName) {
        this.cityName = cityName;
        this.cityItemQuantities = new HashMap<>();
        this.maxItemCapacities = new HashMap<>();
        this.buyPrices = new HashMap<>();
        this.sellPrices = new HashMap<>();
    }

    // Returns the city name
    public String getName() {
        return cityName;
    }

    // Sets the max allowable quantity for a given item
    public void setMaxCapacityForItem(String itemName, int capacity) {
        maxItemCapacities.put(itemName, capacity);
    }

    // Returns the max allowable quantity for a given item
    public int getMaxCapacityForItem(String itemName) {
        return maxItemCapacities.getOrDefault(itemName, 0);
    }

    // Returns the total quantity of all items in the city's inventory
    public int getCurrentCitiesInventorySize() {
        return cityItemQuantities.values().stream().mapToInt(Integer::intValue).sum();
    }

    // Adjusts the quantity of a specific item in the city's inventory
    public void adjustInventory(Item item, int quantity) {
        int currentQuantity = cityItemQuantities.getOrDefault(item.getName(), 0);
        int newQuantity = currentQuantity + quantity;

        // Ensure the adjusted quantity isn't below 0 or over the max allowable capacity
        newQuantity = Math.max(0, newQuantity);
        newQuantity = Math.min(getMaxCapacityForItem(String.valueOf(item)), newQuantity);

        cityItemQuantities.put(item.getName(), newQuantity);
    }

    // Returns the current quantity of a specific item
    public int getItemQuantity(Item item) {
        return cityItemQuantities.getOrDefault(item.getName(), 0);
    }

    // Sets initial prices for an item based on its base price and a random fluctuation
    public void initializeItemPrices(Item item) {
        double basePrice = item.getBasePrice();
        double fluctuation = (Math.random() * 10) - 5;

        buyPrices.put(item.getName(), roundOff(basePrice + fluctuation));
        sellPrices.put(item.getName(), roundOff(basePrice + fluctuation));
    }
    public void initializeInventory(Map<String, Item> itemsMap) {
        for (String itemName : itemsMap.keySet()) {
            int maxCapacity = getMaxCapacityForItem(itemName);
            int randomQuantity = RAND.nextInt(maxCapacity + 1); // +1 because nextInt is exclusive for the upper bound
            cityItemQuantities.put(itemName, randomQuantity);
        }
    }

    // Updates the prices for an item based on its current quantity in the city
    public void updatePricesForItem(Item item) {
        double currentQuantity = getItemQuantity(item);
        double basePrice = item.getBasePrice();

        // Adjust the prices based on current inventory level
        if (currentQuantity < getMaxCapacityForItem(String.valueOf(item)) / 4) {
            buyPrices.put(item.getName(), roundOff(basePrice * 1.2)); // Increase buy price if quantity is low
            sellPrices.put(item.getName(), roundOff(basePrice * 0.9)); // Decrease sell price if quantity is low
        } else if (currentQuantity > 3 * getMaxCapacityForItem(String.valueOf(item)) / 4) {
            buyPrices.put(item.getName(), roundOff(basePrice * 0.8)); // Decrease buy price if quantity is high
            sellPrices.put(item.getName(), roundOff(basePrice * 1.1)); // Increase sell price if quantity is high
        } else {
            buyPrices.put(item.getName(), basePrice);
            sellPrices.put(item.getName(), basePrice);
        }
    }

    // Returns the buy price for an item
    public double getBuyPrice(Item item) {
        return buyPrices.getOrDefault(item.getName(), item.getBasePrice());
    }

    // Returns the sell price for an item
    public double getSellPrice(Item item) {
        return sellPrices.getOrDefault(item.getName(), item.getBasePrice());
    }

    // Helper method to round off a double value to two decimal places
    private double roundOff(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}