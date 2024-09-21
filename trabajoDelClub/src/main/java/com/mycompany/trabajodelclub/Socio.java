/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabajodelclub;
import java.util.ArrayList;

/**
 *
 * @author ssuaz
 */

public class Socio {
    protected String cedula;
    protected String nombre;
    protected double fondosDisponibles;
    protected String tipoSuscripcion; // "VIP" o "Regular"
    protected ArrayList<Factura> facturasPendientes;
    protected ArrayList<AfiliadoAutorizado> personasAutorizadas;

    private static final double FONDO_INICIAL_REGULAR = 50000;
    private static final double FONDO_INICIAL_VIP = 100000;
    private static final double LIMITE_FONDOS_REGULAR = 1000000;
    private static final double LIMITE_FONDOS_VIP = 5000000;
    private static int sociosVIP = 0;

    // Constructor
    public Socio(String cedula, String nombre, String tipoSuscripcion) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.tipoSuscripcion = tipoSuscripcion;
        this.facturasPendientes = new ArrayList<>();
        this.personasAutorizadas = new ArrayList<>();

        if (tipoSuscripcion.equalsIgnoreCase("VIP")) {
            if (sociosVIP >= 3) {
                throw new IllegalArgumentException("No se pueden afiliar más de 3 socios VIP.");
            }
            this.fondosDisponibles = FONDO_INICIAL_VIP;
            sociosVIP++;
        } else {
            this.fondosDisponibles = FONDO_INICIAL_REGULAR;
        }
    }

    // Getters y Setters básicos
    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public double getFondosDisponibles() {
        return fondosDisponibles;
    }

    public String getTipoSuscripcion() {
        return tipoSuscripcion;
    }

    public ArrayList<Factura> getFacturasPendientes() {
        return facturasPendientes;
    }

    public ArrayList<AfiliadoAutorizado> getPersonasAutorizadas() {
        return personasAutorizadas;
    }

    // Métodos para gestionar fondos
    public void agregarFondos(double monto) {
        if (tipoSuscripcion.equalsIgnoreCase("VIP") && fondosDisponibles + monto > LIMITE_FONDOS_VIP) {
            throw new IllegalArgumentException("El fondo máximo para socios VIP es de $5.000.000");
        } else if (tipoSuscripcion.equalsIgnoreCase("Regular") && fondosDisponibles + monto > LIMITE_FONDOS_REGULAR) {
            throw new IllegalArgumentException("El fondo máximo para socios regulares es de $1.000.000");
        }
        this.fondosDisponibles += monto;
    }

    // Métodos para gestionar personas autorizadas
    public void agregarPersonaAutorizada(AfiliadoAutorizado afiliado) {
        if (personasAutorizadas.size() >= 10) {
            throw new IllegalArgumentException("No se pueden agregar más de 10 personas autorizadas.");
        }
        this.personasAutorizadas.add(afiliado);
    }

    public void eliminarPersonaAutorizada(AfiliadoAutorizado afiliado) {
        if (afiliado.getFacturasPendientes().size() > 0) {
            throw new IllegalArgumentException("El afiliado tiene facturas pendientes.");
        }
        this.personasAutorizadas.remove(afiliado);
    }

    // Métodos para gestionar facturas
    public void generarFactura(String concepto, double valor) {
        if (fondosDisponibles < valor) {
            throw new IllegalArgumentException("Fondos insuficientes para generar la factura.");
        }
        Factura nuevaFactura = new Factura(concepto, valor, this.nombre);
        this.facturasPendientes.add(nuevaFactura);
        this.fondosDisponibles -= valor;
    }

    public void pagarFactura(int indiceFactura) {
        Factura factura = this.facturasPendientes.get(indiceFactura);
        if (fondosDisponibles < factura.getValor()) {
            throw new IllegalArgumentException("Fondos insuficientes para pagar la factura.");
        }
        this.fondosDisponibles -= factura.getValor();
        this.facturasPendientes.remove(indiceFactura);
    }
}
