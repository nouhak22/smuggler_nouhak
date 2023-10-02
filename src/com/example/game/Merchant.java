package com.example.game;

import java.util.*;
public class Merchant {
    private int money;
    private String name;
    public static final int MAX_ITEM_CAPACITY = 10000;
    public static final int MAX_TOTAL_CAPACITY = 50000;
    public int totalItemsHeld;
    private final Map<Item, Integer> inventory; // Inventory to store items and their counts
    // Constructor
    public Merchant(int startMoney, String name) {
        this.name = name;
        this.money = startMoney;
        this.totalItemsHeld = 0;
        this.inventory = new HashMap<>();
    }
    public String getName() {
        return name;
    }
    // Method to retrieve quantity of a given item
    public int getItemQuantity(Item item) {
        return inventory.getOrDefault(item, 0);
    }
    // Getter method for money
    public int getMoney() {
        return money;
    }
    // Method to check if a certain quantity of an item can be added to inventory
    public boolean canAddItem(Item item, int quantity) {
        return totalItemsHeld + quantity <= MAX_TOTAL_CAPACITY && getItemQuantity(item) + quantity <= MAX_ITEM_CAPACITY;
    }
    // Method to purchase an item
    public void purchaseItem(Item item, int quantity, Double totalCost) {
        inventory.put(item, getItemQuantity(item) + quantity);
        totalItemsHeld += quantity;
        money -= totalCost;
    }
    // Method to check if a certain quantity of an item can be sold
    public boolean canSellItem(Item item, int quantity) {
        return getItemQuantity(item) >= quantity;
    }
    // Method to sell an item
    public void sellItem(Item item, int quantity, Double totalRevenue) {
        int currentQuantity = getItemQuantity(item) - quantity;
        if (currentQuantity == 0) {
            inventory.remove(item);
        } else {
            inventory.put(item, currentQuantity);
        }
        totalItemsHeld -= quantity;
        money += totalRevenue;  // Money should increase
    }
    // Method to deduct a certain amount from money
    public void deductMoney(int amount) {
        money -= amount;
    }
    // Method to get a random item from the inventory
    public Item getRandomItemFromInventory() {
        Random random = new Random();
        List<Item> items = new ArrayList<>(inventory.keySet());
        return items.get(random.nextInt(items.size()));
    }
    // Method to remove a random item without being paid for it
    public void removeRandomItem() { // Renamed for clarity
        if (inventory.isEmpty()) return;

        Random random = new Random();
        List<Item> items = new ArrayList<>(inventory.keySet());
        Item randomItem = items.get(random.nextInt(items.size()));

        int quantityRemoved = getItemQuantity(randomItem);
        inventory.remove(randomItem);
        totalItemsHeld -= quantityRemoved;
    }
    public int getTotalItemsHeld() {
        return totalItemsHeld;
    }
    public Map<Item, Integer> getInventory() {
        return Collections.unmodifiableMap(inventory);  // This returns an unmodifiable view of the inventory
    }
    public double getFillPercentage() {
        return (double) totalItemsHeld / MAX_TOTAL_CAPACITY;
    }

}

