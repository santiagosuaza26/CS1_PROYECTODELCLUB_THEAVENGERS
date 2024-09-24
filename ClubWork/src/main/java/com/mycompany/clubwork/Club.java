/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clubwork;

import java.util.ArrayList;

/**
 *
 * @author ssuaz
 */
public class Club {
    private ArrayList<Member> members;
    private int vipMembersCount = 0;

    public Club() {
        members = new ArrayList<>();
    }

    public void addMember(Member member) {
        for (Member m : members) {
            if (m.getId() == member.getId()) {
                throw new IllegalArgumentException("A member with this ID already exists.");
            }
        }
        if (members.size() >= 35) {
            throw new IllegalArgumentException("Cannot enroll more than 35 members.");
        }
        members.add(member);
        if (member.getSubscriptionType().equalsIgnoreCase("VIP")) {
            vipMembersCount++;
        }
    }

    public Member findMember(String id) {
        return findMember(id);
    }

    public Member findMember(int id) {
        for (Member m : members) {
            if (m.getId() == id) {
                return m;
            }
        }
        return null;
    }

    public void removeMember(int id) {
        Member member = findMember(id);
        if (member == null) {
            throw new IllegalArgumentException("No member found with that ID.");
        }
        members.remove(member);
    }

    public void showMembers() {
        for (Member m : members) {
            System.out.println("ID: " + m.getId() + ", Name: " + m.getName() + ", Funds: " + m.getAvailableFunds());
        }
    }

    // New method to show authorized persons and their affiliated members
    public void showAuthorizedPersons() {
        for (Member m : members) {
            if (!m.getAuthorizedPersons().isEmpty()) {
                System.out.println("Member: " + m.getName() + " (ID: " + m.getId() + ")");
                for (AuthorizedAffiliate authorized : m.getAuthorizedPersons()) {
                    System.out.println("    Authorized Person: " + authorized.getName() + " (ID: " + authorized.getId() + ")");
                }
            }
        }
    }
}
