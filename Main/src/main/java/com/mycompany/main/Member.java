package com.mycompany.main;

import java.util.HashSet;
import java.util.ArrayList;

abstract class Member {
    private final String id;
    private final String name;
    private double funds;
    private final HashSet<String> authorizedPeople; 
    private final ArrayList<Bill> unpaidBills; 

    public Member(String id, String name, double initialFunds) {
        this.id = id;
        this.name = name;
        this.funds = initialFunds;
        this.authorizedPeople = new HashSet<>();
        this.unpaidBills = new ArrayList<>(); // Inicializa la lista de facturas
    }

    public abstract double getMaxFunds();
    public abstract String getMemberType();

    // Getters
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
        return new HashSet<>(authorizedPeople); // Retorna una copia para evitar modificaciones externas
    }

    public void generateBill(String concept, double amount) {
        if (funds >= amount) { // Verifica si hay fondos suficientes
            Bill bill = new Bill(concept, amount);
            unpaidBills.add(bill); // Agrega la factura a la lista de facturas sin pagar
            this.funds -= amount; // Descuenta el monto de los fondos del socio
            System.out.println("Bill generated: " + concept + " for amount: " + amount);
        } else {
            System.out.println("Insufficient funds to generate bill.");
        }
    }

    public boolean payBill(String billConcept) {
        for (Bill bill : unpaidBills) {
            if (bill.getConcept().equals(billConcept) && !bill.isPaid()) {
                double billAmount = bill.getAmount();
                if (funds >= billAmount) {
                    funds -= billAmount; 
                    bill.markAsPaid();
                    unpaidBills.remove(bill); 
                    System.out.println("Bill paid: " + billConcept);
                    return true;
                } else {
                    System.out.println("Insufficient funds to pay the bill.");
                    return false;
                }
            }
        }
        System.out.println("Bill not found or already paid.");
        return false;
    }

    public boolean hasUnpaidBills() {
        return !unpaidBills.isEmpty();
    }

    public ArrayList<Bill> getUnpaidBills() {
        return unpaidBills;
    }

    public int getAuthorizedCount() {
        return authorizedPeople.size();
    }
}
