/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

/**
 *
 * @author ssuaz
 */
class RegularMember extends Member {
    public RegularMember(String id, String name) {
        super(id, name, 50000);
    }

    @Override
    public double getMaxFunds() {
        return 1000000;
    }

    @Override
    public String getMemberType() {
        return "Regular";
    }
}
