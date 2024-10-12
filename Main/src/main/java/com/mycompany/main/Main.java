/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.main;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        Club club = new Club();
        String[] options = {"Agregar Socio", "Eiminar Socio", "Agregar fondo", "Agregar persona autorizada", "Pagar factura", "Ver información de socio", "Salir"};
        int choice;

        do {
            choice = JOptionPane.showOptionDialog(null, "Elige una opcion", "Menu club",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0:  // Agregar Socio
                    String id = JOptionPane.showInputDialog("Ingrese Cedula:");
                    // Validación de la cédula
                    if (!id.matches("\\d+")) {
                        JOptionPane.showMessageDialog(null, "La cédula debe contener solo números.");
                        break; // Salir del case sin continuar
                    }

                    String name = JOptionPane.showInputDialog("Ingrese nombre:");
                    String type = JOptionPane.showInputDialog("Ingrese tipo (Regular/VIP):");

                    if (type.equalsIgnoreCase("Regular")) {
                        boolean addMember = club.addMember(new RegularMember(id, name)); 
                    } else if (type.equalsIgnoreCase("VIP")) {
                        club.addMember(new VIPMember(id, name));
                    } else {
                        JOptionPane.showMessageDialog(null, "Tipo inválido.");
                    }
                    break;

                case 1:  // Remove Member
                    id = JOptionPane.showInputDialog("Ingrese cedula del socio a eliminar:");
                    if (club.removeMember(id)) {
                        JOptionPane.showMessageDialog(null, "Socio eliminado.");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se puo eliminar al socio.");
                    }
                    break;

                case 2:  // Add Funds
                    id = JOptionPane.showInputDialog("Ingrese cedula:");
                    double amount = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el monto a agregar:"));
                    Member member = club.members.get(id);  // Asumimos acceso directo para simplicidad
                    if (member != null) {
                        member.addFunds(amount);
                        JOptionPane.showMessageDialog(null, "Fondo agregado.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Socio no tiene fondos.");
                    }
                    break;

                case 3:  // Agregar Persona Autorizada
                    String socioId = JOptionPane.showInputDialog("Ingrese Cedula del Socio:");
                    if (!socioId.matches("\\d+")) {
                        JOptionPane.showMessageDialog(null, "La cédula debe contener solo números.");
                        break; // Salir del case sin continuar
                    }

                    Member socio = club.members.get(socioId);
                    if (socio != null) {
                        String personId = JOptionPane.showInputDialog("Ingrese Cedula de la persona autorizada:");
                        if (!personId.matches("\\d+")) {
                            JOptionPane.showMessageDialog(null, "La cédula debe contener solo números.");
                            break; // Salir del case sin continuar
                        }
                        String personName = JOptionPane.showInputDialog("Ingrese nombre de la persona autorizada:");
                        socio.addAuthorizedPerson(personName);
                        JOptionPane.showMessageDialog(null, "Persona autorizada agregada.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Socio no encontrado.");
                    }
                    break;

                case 4:  // Pay Bill
                    id = JOptionPane.showInputDialog("Ingrese cedula:");
                    member = club.members.get(id);
                    if (member != null) {
                        String bill = JOptionPane.showInputDialog("Ingrese el concepto de la factura a pagar:");
                        if (member.payBill(bill)) {
                            JOptionPane.showMessageDialog(null, "factura pagada.");
                        } else {
                            JOptionPane.showMessageDialog(null, "No se pudo pagar la factura");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Socio no tiene fondo.");
                    }
                    break;

                case 5:  // Show Member Info
                    id = JOptionPane.showInputDialog("ingrese la cedula:");
                    club.showMemberInfo(id);
                    break;

                default:
                    break;
            }

        } while (choice != 6);  // Exit
    }
}
