
package com.mycompany.main;
import java.util.HashSet;
import java.util.HashMap;


abstract class Member {
    private final String id;
    private final String name;
    private double funds;
    private final HashSet<String> authorizedPeople;
    private final HashMap<String, Double> unpaidBills;

    public Member(String id, String name, double initialFunds) {
        this.id = id;
        this.name = name;
        this.funds = initialFunds;
        this.authorizedPeople = new HashSet<>();
        this.unpaidBills = new HashMap<>();
    }

    public abstract double getMaxFunds();
    public abstract String getMemberType();

    public String getId() { return id; }
    public String getName() { return name; }
    public double getFunds() { return funds; }

    public void addFunds(double amount) {
        if (funds + amount > getMaxFunds()) {
            System.out.println("Exceeds maximum allowed funds.");
        } else {
            this.funds += amount;
        }
    }

    public void addAuthorizedPerson(String person) {
        if (authorizedPeople.size() < 10) {
            authorizedPeople.add(person);
        } else {
            System.out.println("Cannot add more authorized people.");
        }
    }
    public HashSet<String> getAuthorizedPeople() {
        return new HashSet<>(authorizedPeople); // Devuelve una copia para evitar modificaciones externas
    }

    public boolean payBill(String bill) {
        if (unpaidBills.containsKey(bill)) {
            double billAmount = unpaidBills.get(bill);
            if (funds >= billAmount) {
                funds -= billAmount;
                unpaidBills.remove(bill);
                return true;
            } else {
                System.out.println("Insufficient funds to pay the bill.");
            }
        }
        return false;
    }

    public void addBill(String concept, double value) {
        if (unpaidBills.size() < 20) {
            unpaidBills.put(concept, value);
        } else {
            System.out.println("Cannot add more unpaid bills.");
        }
    }
    
    public boolean hasUnpaidBills() {
        return !unpaidBills.isEmpty();
    }

    public int getAuthorizedCount() {
        return authorizedPeople.size();
    }
    

}
