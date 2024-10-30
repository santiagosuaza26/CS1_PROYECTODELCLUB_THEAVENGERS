package com.mycompany.main;

import java.io.*;
import java.util.HashMap;

public class FileHandler {

    public void guardarDatos(HashMap<String, Member> members) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("club_data.txt"))) {
            for (Member member : members.values()) {
                
                writer.write(member.getId() + "," + member.getName() + "," + member.getClass().getSimpleName() + "," + member.getFunds());
                writer.newLine();
                
                for (Bill bill : member.getUnpaidBills()) {
                    writer.write("FACTURA PENDIENTE," + member.getId() + "," + bill.getConcept() + "," + bill.getAmount() + "," + bill.isPaid());
                    writer.newLine();
                }
                
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
                
                if (line.trim().isEmpty()) {
                    continue; 
                }

                String[] data = line.split(",");
                if (data.length < 4) {
                    System.out.println("Línea invalida: " + line);
                    continue; 
                }
                
                String id = data[0];
                String name = data[1];
                String type = data[2];
                double funds = Double.parseDouble(data[3]);

                Member member;
                if (club.members.containsKey(id)) {
                    
                    member = club.members.get(id);
                } else {
                    
                    if ("RegularMember".equals(type)) {
                        member = new RegularMember(id, name);
                    } else if ("VIPMember".equals(type)) {
                        member = new VIPMember(id, name);
                    } else {
                        System.out.println("Tipo de miembro desconocido: " + type);
                        continue; 
                    }
                    club.members.put(id, member);
                }
                
                member.addFunds(funds - member.getFunds());
                
                while ((line = reader.readLine()) != null) {
                    
                    if (line.trim().isEmpty()) {
                        continue; 
                    }
                    
                    if (line.equals("---------------------------------")) {
                        break; 
                    }

                    String[] billData = line.split(",");
                    if (billData.length < 5) {
                        System.out.println("Factura : " + line);
                        continue; 
                    }
                    
                    String billConcept = billData[2];
                    double billAmount = Double.parseDouble(billData[3]);
                    boolean isPaid = Boolean.parseBoolean(billData[4]);
                    
                    Bill bill = new Bill(billConcept, billAmount);
                    if (isPaid) {
                        bill.markAsPaid();
                    }
                    member.getUnpaidBills().add(bill); 
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
