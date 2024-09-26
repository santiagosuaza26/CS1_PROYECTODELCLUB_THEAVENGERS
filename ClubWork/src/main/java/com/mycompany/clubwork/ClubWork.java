/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.clubwork;

import java.util.Scanner;

/**
 *
 * @author ssuaz
 */

public class ClubWork {
    public static void main(String[] args) {
        // Creamos una nueva instancia del Club
        Club club = new Club();
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            // Mostrar el menú de opciones
            System.out.println("\n--- Menú del Club Social ---");
            System.out.println("1. Inscribir Miembro");
            System.out.println("2. Registrar Persona Autorizada");
            System.out.println("3. Pagar Factura");
            System.out.println("4. Registrar Consumo");
            System.out.println("5. Añadir Fondos");
            System.out.println("6. Eliminar Miembro");
            System.out.println("7. Mostrar Miembros");
            System.out.println("8. Mostrar Personas Autorizadas y sus Miembros");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();  // Leer opción del usuario

            switch (option) {
                case 1:
                    // Inscribir un nuevo miembro
                    System.out.print("Ingrese la cédula del miembro: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();  // Limpiar el buffer del scanner
                    
                    System.out.print("Ingrese el nombre del miembro: ");
                    String name = scanner.next();
                    scanner.nextLine();  // Limpiar el buffer del scanner
                    
                    System.out.print("Ingrese el tipo de suscripción (VIP o Regular): ");
                    String subscriptionType = scanner.next();
                    scanner.nextLine();  // Limpiar el buffer del scanner
                    
                    try {
                        // Crear un nuevo miembro con los datos proporcionados
                        Member newMember = new Member(id, name, subscriptionType);
                        // Añadir el nuevo miembro al club
                        club.addMember(newMember);
                        System.out.println("Miembro inscrito exitosamente.");
                    } catch (Exception e) {
                        // Mostrar error si algo salió mal
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    // Registrar una persona autorizada para un miembro
                    System.out.print("Ingrese la cédula del miembro: ");
                    id = scanner.nextInt();  // Leer cédula del miembro
                    Member member = club.findMember(id);  // Buscar el miembro en el club
                    if (member == null) {
                        System.out.println("No se encontró ningún miembro con esa cédula.");
                    } else {
                        System.out.print("Ingrese la cédula de la persona autorizada: ");
                        int authorizedId = scanner.nextInt();  // Leer cédula de la persona autorizada
                        scanner.nextLine();  // Limpiar el buffer del scanner
                        
                        System.out.print("Ingrese el nombre de la persona autorizada: ");
                        String authorizedName = scanner.next();  // Leer nombre de la persona autorizada
                        scanner.nextLine();  // Limpiar el buffer del scanner
                        
                        try {
                            // Crear una nueva persona autorizada
                            AuthorizedAffiliate newAuthorized = new AuthorizedAffiliate(authorizedId, authorizedName);
                            // Añadir la persona autorizada al miembro, pasando también el club para validar
                            member.addAuthorizedPerson(newAuthorized, club);
                            System.out.println("Persona autorizada registrada exitosamente.");
                        } catch (Exception e) {
                            // Mostrar error si algo salió mal
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                    break;

                case 3:
                    // Pagar una factura pendiente
                    System.out.print("Ingrese la cédula del miembro: ");
                    id = scanner.nextInt();
                    member = club.findMember(id);  // Buscar el miembro
                    if (member == null) {
                        System.out.println("No se encontró ningún miembro con esa cédula.");
                    } else if (member.getPendingInvoices().isEmpty()) {
                        // Si no tiene facturas pendientes
                        System.out.println("Este miembro no tiene facturas pendientes.");
                    } else {
                        // Mostrar facturas pendientes
                        System.out.println("Facturas pendientes:");
                        for (int i = 0; i < member.getPendingInvoices().size(); i++) {
                            Invoice invoice = member.getPendingInvoices().get(i);
                            System.out.println((i + 1) + ". Concepto: " + invoice.getConcept() + ", Monto: " + invoice.getAmount());
                        }
                        System.out.print("Seleccione el número de la factura que desea pagar: ");
                        int invoiceNumber = scanner.nextInt();  // Leer número de factura
                        try {
                            // Pagar la factura seleccionada
                            member.payInvoice(invoiceNumber - 1);  // -1 porque las listas empiezan en 0
                            System.out.println("Factura pagada exitosamente.");
                        } catch (Exception e) {
                            // Mostrar error si algo salió mal
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                    break;

                case 4:
                    // Registrar un consumo
                    System.out.print("Ingrese la cédula del miembro: ");
                    id = scanner.nextInt();
                    member = club.findMember(id);  // Buscar el miembro
                    if (member == null) {
                        System.out.println("No se encontró ningún miembro con esa cédula.");
                    } else {
                        // Registrar un nuevo consumo
                        System.out.print("Ingrese el concepto del consumo: ");
                        String concept = scanner.next();
                        System.out.print("Ingrese el monto del consumo: ");
                        double amount = scanner.nextDouble();
                        try {
                            // Generar la factura por el consumo registrado
                            member.generateInvoice(concept, amount);
                            System.out.println("Consumo registrado exitosamente.");
                        } catch (Exception e) {
                            // Mostrar error si algo salió mal
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                    break;

                case 5:
                    // Añadir fondos a la cuenta de un miembro
                    System.out.print("Ingrese la cédula del miembro: ");
                    id = scanner.nextInt();
                    member = club.findMember(id);  // Buscar el miembro
                    if (member == null) {
                        System.out.println("No se encontró ningún miembro con esa cédula.");
                    } else {
                        // Añadir fondos al miembro
                        System.out.print("Ingrese la cantidad de fondos a añadir: ");
                        double amountToAdd = scanner.nextDouble();
                        try {
                            // Añadir los fondos
                            member.addFunds(amountToAdd);
                            System.out.println("Fondos añadidos exitosamente.");
                        } catch (Exception e) {
                            // Mostrar error si algo salió mal
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                    break;

                case 6:
                    // Eliminar un miembro
                    System.out.print("Ingrese la cédula del miembro que desea eliminar: ");
                    id = scanner.nextInt();
                    try {
                        // Eliminar el miembro del club
                        club.removeMember(id);
                        System.out.println("Miembro eliminado exitosamente.");
                    } catch (Exception e) {
                        // Mostrar error si algo salió mal
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 7:
                    // Mostrar la lista de miembros
                    club.showMembers();
                    break;

                case 8:
                    // Mostrar las personas autorizadas y sus miembros correspondientes
                    club.showAuthorizedPersons();
                    break;

                case 0:
                    // Salir del programa
                    System.out.println("Saliendo...");
                    break;

                default:
                    // Opción no válida
                    System.out.println("Opción inválida.");
            }
        } while (option != 0);  // Seguir mostrando el menú hasta que el usuario seleccione la opción 0 (salir)

        scanner.close();  // Cerrar el scanner cuando terminamos
    }
}


