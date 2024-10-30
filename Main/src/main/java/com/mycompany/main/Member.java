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
        this.unpaidBills = new ArrayList<>();
    }

    public abstract double getMaxFunds();
    public abstract String getMemberType();
    
    public String getId() { return id; }
    public String getName() { return name; }
    public double getFunds() { return funds; }

    public void addFunds(double amount) {
        if (funds + amount > getMaxFunds()) {
            System.out.println("Supera los fondos maximos permitidos.");
        } else {
            this.funds += amount;
        }
    }

    public void addAuthorizedPerson(String person) {
        if (authorizedPeople.size() < 10) {
            authorizedPeople.add(person);
        } else {
            System.out.println("No se pueden agregar mas personas autorizadas.");
        }
    }

    public HashSet<String> getAuthorizedPeople() {
        return new HashSet<>(authorizedPeople); // Retorna una copia para evitar modificaciones externas
    }

    public void generateBill(String concept, double amount) {
        
        Bill bill = new Bill(concept, amount);
        unpaidBills.add(bill); // Agrega la factura a la lista de facturas sin pagar
        System.out.println("Factura generada: " + concept + " por cantidad: " + amount);
    }

    public boolean payBill(String billConcept) {
        for (Bill bill : unpaidBills) {
            if (bill.getConcept().equals(billConcept) && !bill.isPaid()) {
                double billAmount = bill.getAmount();
                if (funds >= billAmount) {
                    funds -= billAmount; 
                    bill.markAsPaid();
                    unpaidBills.remove(bill); 
                    System.out.println("Factura pagada: " + billConcept);
                    return true;
                } else {
                    System.out.println("Fondos insuficientes para pagar la factura.");
                    return false;
                }
            }
        }
        System.out.println("Factura no encontrada.");
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
