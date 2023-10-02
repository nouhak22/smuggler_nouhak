package com.example.game;

public class Item {

    private final String itemName;

    // The base price of the item.
    private final Double basePrice;

    /**
     * Constructor for the Item class.
     */
    public Item(String itemName, Double basePrice) {
        this.itemName = itemName;
        this.basePrice = basePrice;
    }

    /**
     * Getter method for the item's name.
     */
    public String getName() {
        return itemName;
    }

    /**
     * Getter method for the item's base price.
     */
    public Double getBasePrice() {
        return basePrice;
    }
}

