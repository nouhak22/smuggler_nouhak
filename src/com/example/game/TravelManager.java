package com.example.game;

import java.util.Random;
import java.util.List;
import java.util.Scanner;

public class TravelManager {
    private int caughtFine = 100;
    private int timesCaught = 0;
    private List<City> cities;
    private static City currentCity;
    public TravelManager(List<City> cities) {
        this.cities = cities;
        Random rand = new Random();
        this.currentCity = cities.get(rand.nextInt(cities.size()));  // Set a random city
    }
    public void setCurrentCity(City city) {
        this.currentCity = city;
    }
    public City getCurrentCity() {
        return this.currentCity;
    }
    public String getCurrentCityName() {
        return this.currentCity.getName();
    }
    public void travelTo(City destination) {
        setCurrentCity(destination);
        System.out.println("Traveling to " + destination.getName() + "...");
    }
    public void attemptToTravel(Merchant merchant, Scanner scanner) {

        Menu.travel(this, Menu.allCities, scanner);

        if (checkIfCaught(merchant)) {
            System.out.println("Uh oh! You've been caught!");
            applyPenalties(merchant);
            System.out.println("You had an item confiscated and you've been fined. New money total: " + merchant.getMoney());
        } else {
            System.out.println("You successfully traveled to " + getCurrentCity().getName());
        }
    }
    public boolean checkIfCaught(Merchant merchant) {
        double fillPercentage = (double) merchant.getTotalItemsHeld() / Merchant.MAX_TOTAL_CAPACITY;
        int probability = (int) (fillPercentage * 100);
        Random random = new Random();
        return random.nextInt(100) < probability;
    }
    public void applyPenalties(Merchant merchant) {
        Item itemToRemove = merchant.getRandomItemFromInventory();
        int quantityToRemove = merchant.getItemQuantity(itemToRemove);
        merchant.removeRandomItem();

        merchant.deductMoney(caughtFine);
        caughtFine += 50;
        timesCaught++;
    }
    public int getTimesCaught() {
        return timesCaught;
    }
}


