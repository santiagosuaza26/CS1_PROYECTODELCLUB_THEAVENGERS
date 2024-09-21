/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.trabajodelclub;
import java.util.Scanner;

/**
 *
 * @author ssuaz
 */


public class TrabajoDelClub {
    public static void main(String[] args) {
        Club club = new Club();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- Menú del Club Social ---");
            System.out.println("1. Afiliar Socio");
            System.out.println("2. Registrar Persona Autorizada");
            System.out.println("3. Pagar Factura");
            System.out.println("4. Registrar Consumo");
            System.out.println("5. Aumentar Fondos");
            System.out.println("6. Eliminar Socio");
            System.out.println("7. Mostrar Socios");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Afiliar un nuevo socio
                    System.out.print("Ingrese la cedula del socio: ");
                    String cedula = scanner.next();
                    System.out.print("Ingrese el nombre del socio: ");
                    String nombre = scanner.next();
                    System.out.print("Ingrese el tipo de suscripcion (VIP o Regular): ");
                    String tipoSuscripcion = scanner.next();
                    try {
                        Socio nuevoSocio = new Socio(cedula, nombre, tipoSuscripcion);
                        club.agregarSocio(nuevoSocio);
                        System.out.println("Socio afiliado exitosamente.");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    // Registrar una persona autorizada por un socio
                    System.out.print("Ingrese la cedula del socio: ");
                    cedula = scanner.next();
                    Socio socio = club.buscarSocio(cedula);
                    if (socio == null) {
                        System.out.println("No se encontro un socio con esa cedula.");
                    } else {
                        System.out.print("Ingrese la cedula de la persona autorizada: ");
                        String cedulaAutorizada = scanner.next();
                        System.out.print("Ingrese el nombre de la persona autorizada: ");
                        String nombreAutorizado = scanner.next();
                        try {
                            AfiliadoAutorizado nuevoAutorizado = new AfiliadoAutorizado(cedulaAutorizada, nombreAutorizado);
                            socio.agregarPersonaAutorizada(nuevoAutorizado);
                            System.out.println("Persona autorizada registrada exitosamente.");
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                    break;

                case 3:
                    // Pagar una factura
                    System.out.print("Ingrese la cedula del socio: ");
                    cedula = scanner.next();
                    socio = club.buscarSocio(cedula);
                    if (socio == null) {
                        System.out.println("No se encontro un socio con esa cedula.");
                    } else if (socio.getFacturasPendientes().isEmpty()) {
                        System.out.println("No hay facturas pendientes para este socio.");
                    } else {
                        System.out.println("Facturas pendientes:");
                        for (int i = 0; i < socio.getFacturasPendientes().size(); i++) {
                            Factura factura = socio.getFacturasPendientes().get(i);
                            System.out.println((i + 1) + ". Concepto: " + factura.getConcepto() + ", Valor: " + factura.getValor());
                        }
                        System.out.print("Seleccione el numero de la factura que desea pagar: ");
                        int numeroFactura = scanner.nextInt();
                        try {
                            socio.pagarFactura(numeroFactura - 1);
                            System.out.println("Factura pagada exitosamente.");
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                    break;

                case 4:
                    // Registrar un consumo
                    System.out.print("Ingrese la cedula del socio: ");
                    cedula = scanner.next();
                    socio = club.buscarSocio(cedula);
                    if (socio == null) {
                        System.out.println("No se encontro un socio con esa cedula.");
                    } else {
                        System.out.print("Ingrese el concepto del consumo: ");
                        String concepto = scanner.next();
                        System.out.print("Ingrese el valor del consumo: ");
                        double valor = scanner.nextDouble();
                        try {
                            socio.generarFactura(concepto, valor);
                            System.out.println("Consumo registrado exitosamente.");
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                    break;

                case 5:
                    // Aumentar fondos de la cuenta de un socio
                    System.out.print("Ingrese la cedula del socio: ");
                    cedula = scanner.next();
                    socio = club.buscarSocio(cedula);
                    if (socio == null) {
                        System.out.println("No se encontro un socio con esa cedula.");
                    } else {
                        System.out.print("Ingrese el monto que desea agregar: ");
                        double monto = scanner.nextDouble();
                        try {
                            socio.agregarFondos(monto);
                            System.out.println("Fondos aumentados exitosamente.");
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                    break;

                case 6:
                    // Eliminar un socio
                    System.out.print("Ingrese la cedula del socio que desea eliminar: ");
                    cedula = scanner.next();
                    try {
                        club.eliminarSocio(cedula);
                        System.out.println("Socio eliminado exitosamente.");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 7:
                    // Mostrar lista de socios
                    club.mostrarSocios();
                    break;

                case 0:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opcion no valida.");
            }
        } while (opcion != 0);

        scanner.close();
    }
}
