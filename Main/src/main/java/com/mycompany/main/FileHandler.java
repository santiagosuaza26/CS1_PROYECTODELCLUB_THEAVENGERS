package com.mycompany.main;

import java.io.*;
import java.util.HashMap;

public class FileHandler {

    public void guardarDatos(HashMap<String, Member> members) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("club_data.txt"))) {
            for (Member member : members.values()) {
                // Guarda el id, nombre, tipo y fondos
                writer.write(member.getId() + "," + member.getName() + "," + member.getClass().getSimpleName() + "," + member.getFunds());
                writer.newLine();
                
                // Guarda facturas pendientes
                for (Bill bill : member.getUnpaidBills()) {
                    writer.write("FACTURA PENDIENTE," + member.getId() + "," + bill.getConcept() + "," + bill.getAmount() + "," + bill.isPaid());
                    writer.newLine();
                }

                // Añadir una línea divisoria para mayor claridad
                writer.write("----"); // Línea divisoria entre miembros
                writer.newLine();
            }
            System.out.println("Datos guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar los datos: " + e.getMessage());
        }
    }

    public void cargarDatos(Club club) {
        try (BufferedReader reader = new BufferedReader(new FileReader("club_data.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Comprobación de líneas vacías
                if (line.trim().isEmpty()) {
                    continue; // Salta líneas vacías
                }

                String[] data = line.split(",");
                if (data.length < 4) {
                    System.out.println("Línea invalida: " + line);
                    continue; // Salta líneas inválidas
                }
                
                String id = data[0];
                String name = data[1];
                String type = data[2];
                double funds = Double.parseDouble(data[3]);

                Member member;
                if (club.members.containsKey(id)) {
                    // Si el miembro ya existe, no se vuelve a agregar, solo se usan sus datos
                    member = club.members.get(id);
                } else {
                    // Si el miembro no existe, lo creamos
                    if ("RegularMember".equals(type)) {
                        member = new RegularMember(id, name);
                    } else if ("VIPMember".equals(type)) {
                        member = new VIPMember(id, name);
                    } else {
                        System.out.println("Tipo de miembro desconocido: " + type);
                        continue; // Salta este miembro si el tipo es desconocido
                    }
                    club.members.put(id, member);
                }

                // Establece los fondos (no agrega, solo establece el saldo actual)
                member.addFunds(funds - member.getFunds()); // Actualiza solo si los fondos son diferentes

                // Cargar facturas pendientes
                while ((line = reader.readLine()) != null) {
                    // Comprobación de líneas vacías
                    if (line.trim().isEmpty()) {
                        continue; // Salta líneas vacías
                    }
                    
                    // Si encontramos una línea divisoria, salimos del bucle
                    if (line.equals("---------------------------------")) {
                        break; // Sale del bucle si llega a la línea divisoria
                    }

                    String[] billData = line.split(",");
                    if (billData.length < 5) {
                        System.out.println("Linea de factura invalida: " + line);
                        continue; // Salta líneas inválidas
                    }
                    
                    String billConcept = billData[2];
                    double billAmount = Double.parseDouble(billData[3]);
                    boolean isPaid = Boolean.parseBoolean(billData[4]);
                    
                    Bill bill = new Bill(billConcept, billAmount);
                    if (isPaid) {
                        bill.markAsPaid();
                    }
                    member.getUnpaidBills().add(bill); // Agrega la factura a las facturas pendientes
                }
            }
            System.out.println("Datos cargados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al cargar los datos: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error en formato numérico: " + e.getMessage());
        }
    }
}
