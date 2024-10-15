package com.mycompany.main;

import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        Club club = new Club();
        String[] options = {"Agregar Socio", "Eliminar Socio", "Agregar fondo", "Agregar persona autorizada", "Pagar factura", "Ver información de socio", "Hacer Consumo", "Salir"};
        int choice;

        Member member = null; // Declara la variable member una sola vez aquí

        do {
            choice = JOptionPane.showOptionDialog(null, "Elige una opción", "Menu club",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0:  // Agregar Socio
                    String id = JOptionPane.showInputDialog("Ingrese Cédula:");
                    // Validación de la cédula
                    if (!id.matches("\\d+")) {
                        JOptionPane.showMessageDialog(null, "La cédula debe contener solo números.");
                        break; // Salir del case sin continuar
                    }

                    String name = JOptionPane.showInputDialog("Ingrese nombre:");
                    String type = JOptionPane.showInputDialog("Ingrese tipo (Regular/VIP):");

                    if (type.equalsIgnoreCase("Regular")) {
                        club.addMember(new RegularMember(id, name)); 
                    } else if (type.equalsIgnoreCase("VIP")) {
                        club.addMember(new VIPMember(id, name));
                    } else {
                        JOptionPane.showMessageDialog(null, "Tipo inválido.");
                    }
                    break;

                case 1:  // Eliminar Socio
                    id = JOptionPane.showInputDialog("Ingrese cédula del socio a eliminar:");
                    if (club.removeMember(id)) {
                        JOptionPane.showMessageDialog(null, "Socio eliminado.");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo eliminar al socio.");
                    }
                    break;

                case 2:  // Agregar Fondos
                    id = JOptionPane.showInputDialog("Ingrese cédula:");
                    double amount = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el monto a agregar:"));
                    member = club.members.get(id);  // Asumimos acceso directo para simplicidad
                    if (member != null) {
                        member.addFunds(amount);
                        JOptionPane.showMessageDialog(null, "Fondo agregado.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Socio no encontrado.");
                    }
                    break;

                case 3:  // Agregar Persona Autorizada
                    String socioId = JOptionPane.showInputDialog("Ingrese Cédula del Socio:");
                    if (!socioId.matches("\\d+")) {
                        JOptionPane.showMessageDialog(null, "La cédula debe contener solo números.");
                        break; // Salir del case sin continuar
                    }

                    member = club.members.get(socioId);
                    if (member != null) {
                        String personId = JOptionPane.showInputDialog("Ingrese Cédula de la persona autorizada:");
                        if (!personId.matches("\\d+")) {
                            JOptionPane.showMessageDialog(null, "La cédula debe contener solo números.");
                            break; // Salir del case sin continuar
                        }
                        String personName = JOptionPane.showInputDialog("Ingrese nombre de la persona autorizada:");
                        member.addAuthorizedPerson(personName);
                        JOptionPane.showMessageDialog(null, "Persona autorizada agregada.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Socio no encontrado.");
                    }
                    break;

                case 4:  // Pagar Factura
                    id = JOptionPane.showInputDialog("Ingrese cédula:");
                    member = club.members.get(id);
                    if (member != null) {
                        String bill = JOptionPane.showInputDialog("Ingrese el concepto de la factura a pagar:");
                        if (member.payBill(bill)) {
                            JOptionPane.showMessageDialog(null, "Factura pagada.");
                        } else {
                            JOptionPane.showMessageDialog(null, "No se pudo pagar la factura.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Socio no encontrado.");
                    }
                    break;

                case 5:  // Mostrar Información de Socio
                    id = JOptionPane.showInputDialog("Ingrese la cédula:");
                    club.showMemberInfo(id);
                    break;

                case 6:  // Hacer Consumo
                    String memberId = JOptionPane.showInputDialog("Ingrese cédula del socio:");
                    member = club.members.get(memberId);
                    
                    if (member == null) {
                        JOptionPane.showMessageDialog(null, "No está registrado en el sistema.");
                    } else {
                        String billConcept = JOptionPane.showInputDialog("Ingrese concepto del consumo:");
                        double consumptionAmount = Double.parseDouble(JOptionPane.showInputDialog("Ingrese monto del consumo:"));

                        // Verifica si el monto del consumo es mayor que el saldo
                        if (consumptionAmount > member.getFunds()) {
                            JOptionPane.showMessageDialog(null, "Saldo insuficiente.");
                        } else {
                            // Genera la factura
                            member.generateBill(billConcept, consumptionAmount);
                            JOptionPane.showMessageDialog(null, "Factura generada:\n" +
                                    "Concepto de la factura: " + billConcept + "\n" +
                                    "Valor de la factura: " + consumptionAmount + "\n" +
                                    "Nombre del socio: " + member.getName());
                        }
                    }
                    break;
            }

        } while (choice != 7);  // Salir
    }
}
