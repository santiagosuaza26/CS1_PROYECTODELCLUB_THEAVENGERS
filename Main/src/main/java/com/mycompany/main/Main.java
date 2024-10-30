package com.mycompany.main;

import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        Club club = new Club();
        FileHandler fileHandler = new FileHandler(); 

        fileHandler.cargarDatos(club);

        String[] options = {"Agregar Socio", "Eliminar Socio", "Agregar fondo", "Agregar persona autorizada", "Pagar factura", "Ver informacion de socio", "Hacer Consumo", "Salir"};
        int choice;

        Member member = null; 

        do {
            choice = JOptionPane.showOptionDialog(null, "Elige una opcion", "Menu club",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0:  
    String id = JOptionPane.showInputDialog("Ingrese Cedula:");
    if (!id.matches("\\d+")) {
        JOptionPane.showMessageDialog(null, "La cedula debe contener solo numeros.");
        break; 
    }
                    
    if (club.members.containsKey(id)) {
        JOptionPane.showMessageDialog(null, "El socio ya existe. No se puede agregar.");
        break; 
    }

    String name = JOptionPane.showInputDialog("Ingrese nombre:");
    String type = JOptionPane.showInputDialog("Ingrese tipo (Regular/VIP):");

    if (type.equalsIgnoreCase("Regular")) {
        club.addMember(new RegularMember(id, name)); 
        JOptionPane.showMessageDialog(null, "Socio agregado!:\nCedula: " + id + "\nNombre: " + name + "\nTipo: Regular");
    } else if (type.equalsIgnoreCase("VIP")) {
        club.addMember(new VIPMember(id, name));
        JOptionPane.showMessageDialog(null, "Socio agregado:\nCedula: " + id + "\nNombre: " + name + "\nTipo: VIP");
    } else {
        JOptionPane.showMessageDialog(null, "Tipo invalido.");
    }
    break;
                case 1:  
                    id = JOptionPane.showInputDialog("Ingrese cedula del socio a eliminar:");
                    if (club.removeMember(id)) {
                        JOptionPane.showMessageDialog(null, "Socio eliminado.");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo eliminar al socio.");
                    }
                    break;

                case 2: 
                    id = JOptionPane.showInputDialog("Ingrese cedula:");
                    double amount = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el monto a agregar:"));
                    member = club.members.get(id);  
                    if (member != null) {
                        member.addFunds(amount);
                        JOptionPane.showMessageDialog(null, "Fondo agregado.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Socio no encontrado.");
                    }
                    break;

                case 3: 
                    String socioId = JOptionPane.showInputDialog("Ingrese Cedula del Socio:");
                    if (!socioId.matches("\\d+")) {
                        JOptionPane.showMessageDialog(null, "La cedula debe contener solo numeros.");
                        break;
                    }

                    member = club.members.get(socioId);
                    if (member != null) {
                        String personId = JOptionPane.showInputDialog("Ingrese Cédula de la persona autorizada:");
                        if (!personId.matches("\\d+")) {
                            JOptionPane.showMessageDialog(null, "La cedula debe contener solo numeros.");
                            break; 
                        }
                        String personName = JOptionPane.showInputDialog("Ingrese nombre de la persona autorizada:");
                        member.addAuthorizedPerson(personName);
                        JOptionPane.showMessageDialog(null, "Persona autorizada agregada.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Socio no encontrado.");
                    }
                    break;

                case 4:  
                    id = JOptionPane.showInputDialog("Ingrese cedula:");
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

                case 5: 
                    id = JOptionPane.showInputDialog("Ingrese la cedula:");
                    club.showMemberInfo(id);
                    break;

                case 6:  
    String memberId = JOptionPane.showInputDialog("Ingrese cedula del socio:");
    member = club.members.get(memberId);
    
    if (member == null) {
        JOptionPane.showMessageDialog(null, "No esta registrado en el sistema.");
    } else {
        String billConcept = JOptionPane.showInputDialog("Ingrese concepto del consumo:");
        double consumptionAmount;
        
        try {
            consumptionAmount = Double.parseDouble(JOptionPane.showInputDialog("Ingrese monto del consumo:"));
            
            member.generateBill(billConcept, consumptionAmount);
            JOptionPane.showMessageDialog(null, "Factura generada:\n" +
                    "Concepto de la factura: " + billConcept + "\n" +
                    "Valor de la factura: " + consumptionAmount + "\n" +
                    "Nombre del socio: " + member.getName());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un monto valido.");
        }
    }
    break;
            }

        } while (choice != 7);  

        fileHandler.guardarDatos(club.members);
    }
}
