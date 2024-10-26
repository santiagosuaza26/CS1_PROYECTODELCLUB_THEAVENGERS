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
                String[] data = line.split(",");
                String id = data[0];
                String name = data[1];
                String type = data[2];
                double funds = Double.parseDouble(data[3]);

                Member member;
                if ("RegularMember".equals(type)) {
                    member = new RegularMember(id, name);
                } else {
                    member = new VIPMember(id, name);
                }
                member.addFunds(funds); // Añade los fondos cargados
                club.members.put(id, member);
            }
            System.out.println("Datos cargados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al cargar los datos: " + e.getMessage());
        }
    }
}
