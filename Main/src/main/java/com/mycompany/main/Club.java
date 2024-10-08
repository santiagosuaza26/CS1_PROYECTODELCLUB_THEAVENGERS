/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

import java.util.HashMap;

/**
 *
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
            System.out.println("Member with this ID already exists.");
            return false;
        }

        if (member instanceof VIPMember && vipCount >= 3) {
            System.out.println("Cannot add more VIP members.");
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
            System.out.println("Member not found.");
            return false;
        }

        if (member instanceof VIPMember) {
            System.out.println("Cannot remove VIP members.");
            return false;
        }

        if (member.hasUnpaidBills()) {
            System.out.println("Member has unpaid bills.");
            return false;
        }

        if (member.getAuthorizedCount() > 1) {
            System.out.println("Member has more than one authorized person.");
            return false;
        }

        members.remove(id);
        return true;
    }

    public void showMemberInfo(String id) {
        Member member = members.get(id);
        if (member != null) {
            System.out.println("ID: " + member.getId());
            System.out.println("Name: " + member.getName());
            System.out.println("Funds: " + member.getFunds());
            System.out.println("Type: " + member.getMemberType());
        } else {
            System.out.println("Member not found.");
        }
    }
}
