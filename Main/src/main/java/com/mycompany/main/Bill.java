package com.mycompany.main; 

public class Bill {
    private String concept; 
    private double amount;   
    private boolean isPaid;  

    // Constructor
    public Bill(String concept, double amount) {
        this.concept = concept;
        this.amount = amount;
        this.isPaid = false;
    }

    //  getters
    public String getConcept() {
        return concept;
    }

    public double getAmount() {
        return amount;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void markAsPaid() {
        this.isPaid = true; 
    }
}
