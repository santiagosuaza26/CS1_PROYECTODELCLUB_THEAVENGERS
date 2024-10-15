package com.mycompany.main; // Asegúrate de que el paquete sea el correcto

public class Bill {
    private String concept; // Concepto de la factura
    private double amount;   // Monto de la factura
    private boolean isPaid;  // Estado de pago

    // Constructor
    public Bill(String concept, double amount) {
        this.concept = concept;
        this.amount = amount;
        this.isPaid = false; // Inicialmente, la factura no está pagada
    }

    // Métodos getters
    public String getConcept() {
        return concept;
    }

    public double getAmount() {
        return amount;
    }

    public boolean isPaid() {
        return isPaid;
    }

    // Método para marcar la factura como pagada
    public void markAsPaid() {
        this.isPaid = true; // Cambia el estado de pago
    }
}
