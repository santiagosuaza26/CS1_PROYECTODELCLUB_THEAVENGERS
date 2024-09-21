/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabajodelclub;

/**
 *
 * @author ssuaz
 */

public class Factura {
    private String concepto;
    private double valor;
    private String nombreSocio;

    // Constructor
    public Factura(String concepto, double valor, String nombreSocio) {
        this.concepto = concepto;
        this.valor = valor;
        this.nombreSocio = nombreSocio;
    }

    // Getters
    public String getConcepto() {
        return concepto;
    }

    public double getValor() {
        return valor;
    }

    public String getNombreSocio() {
        return nombreSocio;
    }
}
