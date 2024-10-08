/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;
import java.util.HashSet;
import java.util.HashMap;

/**
 *
 * @author ssuaz
 */

abstract class Member {
    private String id;
    private String name;
    private double funds;
    private HashSet<String> authorizedPeople;
    private HashMap<String, Double> unpaidBills;

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

