/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

/**
 *
 * @author ssuaz
 */
class VIPMember extends Member {
    public VIPMember(String id, String name) {
        super(id, name, 100000);
    }

    @Override
    public double getMaxFunds() {
        return 5000000;
    }

    @Override
    public String getMemberType() {
        return "VIP";
    }
}
