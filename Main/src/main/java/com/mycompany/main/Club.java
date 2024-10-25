/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

import java.util.HashMap;

/**
 *
 * 
}

 * @author ssuaz
 */
class Club {
    HashMap<String, Member> members;
    private int vipCount = 0;

    public Club() {
        this.members = new HashMap<>();
    }

    public boolean addMember(Member member) {
        if (members.containsKey(member.getId())) {
            System.out.println("El socio con este cedula ya existe.");
            return false;
        }

        if (member instanceof VIPMember && vipCount >= 3) {
            System.out.println("No se pueden agregar mas socios VIP.");
            return false;
        }

        members.put(member.getId(), member);
        if (member instanceof VIPMember) {
            vipCount++;
        }

        return true;
    }

    public boolean removeMember(String id) {
        Member member = members.get(id);
        if (member == null) {
            System.out.println("socio no encontrado.");
            return false;
        }

        if (member instanceof VIPMember) {
            System.out.println("No se pueden eliminar socios VIP.");
            return false;
        }

        if (member.hasUnpaidBills()) {
            System.out.println("El socio tiene deudas pendientes..");
            return false;
        }

        if (member.getAuthorizedCount() > 1) {
            System.out.println("tiene más de una persona autorizada..");
            return false;
        }

        members.remove(id);
        return true;
    }

    public void showMemberInfo(String id) {
    Member member = members.get(id);
    if (member != null) {
        StringBuilder info = new StringBuilder();
        info.append("Cedula: ").append(member.getId()).append("\n");
        info.append("Nombre: ").append(member.getName()).append("\n");
        info.append("Fondos: ").append(member.getFunds()).append("\n");
        info.append("Tipo: ").append(member.getMemberType()).append("\n");
        info.append("Personas autorizadas: ").append(member.getAuthorizedCount()).append("\n");
        
        if (member.getAuthorizedCount() <= 0) {
            info.append("No hay personas autorizadas.\n");
        } else {
            info.append("Nombres: ").append(String.join(", ", member.getAuthorizedPeople())).append("\n");
        }

        System.out.println(info.toString());
    } else {
        System.out.println("Socio no encontrado.");
    }
}
}
