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

public class Club {
    private ArrayList<Socio> socios;
    private int vipMembersCount = 0;

    public Club() {
        socios = new ArrayList<>();
    }

    public void agregarSocio(Socio socio) {
        for (Socio s : socios) {
            if (s.getCedula().equals(socio.getCedula())) {
                throw new IllegalArgumentException("Ya existe un socio con esta cédula.");
            }
        }
        if (socios.size() >= 35) {
            throw new IllegalArgumentException("No se pueden afiliar más de 35 socios.");
        }
        socios.add(socio);
        if (socio.getTipoSuscripcion().equalsIgnoreCase("VIP")) {
            vipMembersCount++;
        }
    }

    public Socio buscarSocio(String cedula) {
        for (Socio s : socios) {
            if (s.getCedula().equals(cedula)) {
                return s;
            }
        }
        return null;
    }

    public void eliminarSocio(String cedula) {
        Socio socio = buscarSocio(cedula);
        if (socio == null) {
            throw new IllegalArgumentException("No existe un socio con esa cédula.");
        }
        if (socio.getTipoSuscripcion().equalsIgnoreCase("VIP")) {
            throw new IllegalArgumentException("No se pueden eliminar socios VIP.");
        }
        if (!socio.getFacturasPendientes().isEmpty()) {
            throw new IllegalArgumentException("El socio tiene facturas pendientes.");
        }
        if (socio.getPersonasAutorizadas().size() > 1) {
            throw new IllegalArgumentException("El socio tiene más de un autorizado.");
        }
        socios.remove(socio);
    }

    public void mostrarSocios() {
        for (Socio s : socios) {
            System.out.println("Cédula: " + s.getCedula() + ", Nombre: " + s.getNombre() + ", Fondos: " + s.getFondosDisponibles());
        }
    }
}
