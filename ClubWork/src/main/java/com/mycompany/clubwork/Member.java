/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clubwork;

import java.util.ArrayList;

/**
 *
 * @author ssuaz
 */
public class Member {
    protected int id;
    protected String name;
    protected double availableFunds;
    protected String subscriptionType; // "VIP" or "Regular"
    protected ArrayList<Invoice> pendingInvoices;
    protected ArrayList<AuthorizedAffiliate> authorizedPersons;

    private static final double INITIAL_REGULAR_FUNDS = 50000;
    private static final double INITIAL_VIP_FUNDS = 100000;
    private static final double REGULAR_FUNDS_LIMIT = 1000000;
    private static final double VIP_FUNDS_LIMIT = 5000000;
    private static int vipMembers = 0;

    // Constructor
    public Member(int id, String name, String subscriptionType) {
        this.id = id;
        this.name = name;
        this.subscriptionType = subscriptionType;
        this.pendingInvoices = new ArrayList<>();
        this.authorizedPersons = new ArrayList<>();

        if (subscriptionType.equalsIgnoreCase("VIP")) {
            if (vipMembers >= 3) {
                throw new IllegalArgumentException("Cannot enroll more than 3 VIP members.");
            }
            this.availableFunds = INITIAL_VIP_FUNDS;
            vipMembers++;
        } else {
            this.availableFunds = INITIAL_REGULAR_FUNDS;
        }
    }

    // Basic Getters and Setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getAvailableFunds() {
        return availableFunds;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public ArrayList<Invoice> getPendingInvoices() {
        return pendingInvoices;
    }

    public ArrayList<AuthorizedAffiliate> getAuthorizedPersons() {
        return authorizedPersons;
    }

    // Methods to manage funds
    public void addFunds(double amount) {
        if (subscriptionType.equalsIgnoreCase("VIP") && availableFunds + amount > VIP_FUNDS_LIMIT) {
            throw new IllegalArgumentException("The maximum fund limit for VIP members is $5,000,000.");
        } else if (subscriptionType.equalsIgnoreCase("Regular") && availableFunds + amount > REGULAR_FUNDS_LIMIT) {
            throw new IllegalArgumentException("The maximum fund limit for regular members is $1,000,000.");
        }
        this.availableFunds += amount;
    }

    // Methods to manage authorized persons
    public void addAuthorizedPerson(AuthorizedAffiliate affiliate) {
        if (authorizedPersons.size() >= 10) {
            throw new IllegalArgumentException("Cannot add more than 10 authorized persons.");
        }
        this.authorizedPersons.add(affiliate);
    }

    public void removeAuthorizedPerson(AuthorizedAffiliate affiliate) {
        if (affiliate.getPendingInvoices().size() > 0) {
            throw new IllegalArgumentException("The affiliate has pending invoices.");
        }
        this.authorizedPersons.remove(affiliate);
    }

    // Methods to manage invoices
    public void generateInvoice(String concept, double amount) {
        if (availableFunds < amount) {
            throw new IllegalArgumentException("Insufficient funds to generate the invoice.");
        }
        Invoice newInvoice = new Invoice(concept, amount, this.name);
        this.pendingInvoices.add(newInvoice);
        this.availableFunds -= amount;
    }

    public void payInvoice(int invoiceIndex) {
        Invoice invoice = this.pendingInvoices.get(invoiceIndex);
        if (availableFunds < invoice.getAmount()) {
            throw new IllegalArgumentException("Insufficient funds to pay the invoice.");
        }
        this.availableFunds -= invoice.getAmount();
        this.pendingInvoices.remove(invoiceIndex);
    }
}
