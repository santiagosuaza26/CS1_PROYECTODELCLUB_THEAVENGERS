package com.mycompany.main;

import java.util.HashSet;
import java.util.ArrayList;

abstract class Member {
    private final String id;
    private final String name;
    private double funds;
    private final HashSet<String> authorizedPeople; // Personas autorizadas
    private final ArrayList<Bill> unpaidBills; // Lista de facturas sin pagar

    // Constructor de la clase Member
    public Member(String id, String name, double initialFunds) {
        this.id = id;
        this.name = name;
        this.funds = initialFunds;
        this.authorizedPeople = new HashSet<>();
        this.unpaidBills = new ArrayList<>(); // Inicializa la lista de facturas
    }

    // Método abstracto que deben implementar las clases hijas
    public abstract double getMaxFunds();
    public abstract String getMemberType();

    // Getters para id, name, y funds
    public String getId() { return id; }
    public String getName() { return name; }
    public double getFunds() { return funds; }

    // Método para agregar fondos a la cuenta del socio
    public void addFunds(double amount) {
        if (funds + amount > getMaxFunds()) {
            System.out.println("Exceeds maximum allowed funds.");
        } else {
            this.funds += amount;
        }
    }

    // Método para agregar una persona autorizada
    public void addAuthorizedPerson(String person) {
        if (authorizedPeople.size() < 10) {
            authorizedPeople.add(person);
        } else {
            System.out.println("Cannot add more authorized people.");
        }
    }

    // Método para obtener las personas autorizadas
    public HashSet<String> getAuthorizedPeople() {
        return new HashSet<>(authorizedPeople); // Retorna una copia para evitar modificaciones externas
    }

    // Método para generar una factura si el socio tiene fondos suficientes
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

    // Método para pagar una factura
    public boolean payBill(String billConcept) {
        for (Bill bill : unpaidBills) {
            if (bill.getConcept().equals(billConcept) && !bill.isPaid()) {
                double billAmount = bill.getAmount();
                if (funds >= billAmount) {
                    funds -= billAmount; // Descuenta el monto de los fondos del socio
                    bill.markAsPaid(); // Marca la factura como pagada
                    unpaidBills.remove(bill); // Elimina la factura de la lista
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

    // Método para verificar si tiene facturas sin pagar
    public boolean hasUnpaidBills() {
        return !unpaidBills.isEmpty();
    }

    // Método para obtener la lista de facturas sin pagar
    public ArrayList<Bill> getUnpaidBills() {
        return unpaidBills;
    }

    // Método para obtener la cantidad de personas autorizadas
    public int getAuthorizedCount() {
        return authorizedPeople.size();
    }
}
