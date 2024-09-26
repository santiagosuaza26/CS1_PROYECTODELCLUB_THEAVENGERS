/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * Clase que representa un Club Social.
 */
package com.mycompany.clubwork;

import java.util.ArrayList;

/**
 *
 * @author ssuaz
 */
public class Club {
    // Lista de miembros del club
    private ArrayList<Member> members;
    // Contador de miembros VIP
    private int vipMembersCount = 0;

    // Constructor que inicializa la lista de miembros
    public Club() {
        members = new ArrayList<>();
    }

    // Método para agregar un nuevo miembro al club
    public void addMember(Member member) {
        // Verificar si ya existe un miembro con la misma cédula
        for (Member m : members) {
            if (m.getId() == member.getId()) {
                throw new IllegalArgumentException("Ya existe un miembro con esta cédula.");
            }

            // Verificar si algún afiliado autorizado tiene la misma cédula que el nuevo miembro
            for (AuthorizedAffiliate affiliate : m.getAuthorizedPersons()) {
                if (affiliate.getId() == member.getId()) {
                    throw new IllegalArgumentException("Ya existe una persona autorizada con esta cédula.");
                }
            }
        }

        // Verificar si el club ya tiene 35 miembros
        if (members.size() >= 35) {
            throw new IllegalArgumentException("No se pueden inscribir más de 35 miembros.");
        }

        // Agregar el nuevo miembro a la lista
        members.add(member);

        // Si el nuevo miembro es VIP, aumentar el contador de miembros VIP
        if (member.getSubscriptionType().equalsIgnoreCase("VIP")) {
            vipMembersCount++;
        }
    }

    // Método para obtener la lista de todos los miembros
    public ArrayList<Member> getMembers() {
        return members;
    }

    // Método para buscar un miembro por cédula (tipo String)
    public Member findMember(String id) {
        return findMember(id); // Esto está incorrecto, debería corregirse para buscar bien el miembro.
    }

    // Método para buscar un miembro por cédula (tipo int)
    public Member findMember(int id) {
        // Buscar en la lista de miembros
        for (Member m : members) {
            if (m.getId() == id) {
                return m;  // Retorna el miembro si encuentra coincidencia con la cédula
            }
        }
        return null;  // Si no encuentra, retorna null
    }

    // Método para eliminar un miembro del club
    public void removeMember(int id) {
        // Buscar el miembro por su cédula
        Member member = findMember(id);
        if (member == null) {
            throw new IllegalArgumentException("No se encontró un miembro con esa cédula.");
        }
        // Eliminar al miembro de la lista
        members.remove(member);
    }

    // Método para mostrar todos los miembros del club
    public void showMembers() {
        // Recorrer la lista de miembros y mostrar sus datos
        for (Member m : members) {
            System.out.println("Cédula: " + m.getId() + ", Nombre: " + m.getName() + ", Fondos: " + m.getAvailableFunds());
        }
    }

    // Método para mostrar las personas autorizadas de cada miembro y sus datos
    public void showAuthorizedPersons() {
        // Recorrer la lista de miembros
        for (Member m : members) {
            // Verificar si el miembro tiene personas autorizadas
            if (!m.getAuthorizedPersons().isEmpty()) {
                System.out.println("Miembro: " + m.getName() + " (Cédula: " + m.getId() + ")");
                // Mostrar los datos de cada persona autorizada asociada al miembro
                for (AuthorizedAffiliate authorized : m.getAuthorizedPersons()) {
                    System.out.println("    Persona Autorizada: " + authorized.getName() + " (Cédula: " + authorized.getId() + ")");
                }
            }
        }
    }
}

