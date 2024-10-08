/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.main;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        Club club = new Club();
        String[] options = {"Add Member", "Remove Member", "Add Funds", "Add Authorized Person", "Pay Bill", "Show Member Info", "Exit"};
        int choice;

        do {
            choice = JOptionPane.showOptionDialog(null, "Choose an action", "Club Menu",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0:  // Add Member
                    String id = JOptionPane.showInputDialog("Enter ID:");
                    String name = JOptionPane.showInputDialog("Enter Name:");
                    String type = JOptionPane.showInputDialog("Enter Type (Regular/VIP):");

                    if (type.equalsIgnoreCase("Regular")) {
                        club.addMember(new RegularMember(id, name));
                    } else if (type.equalsIgnoreCase("VIP")) {
                        club.addMember(new VIPMember(id, name));
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid type.");
                    }
                    break;

                case 1:  // Remove Member
                    id = JOptionPane.showInputDialog("Enter ID to remove:");
                    if (club.removeMember(id)) {
                        JOptionPane.showMessageDialog(null, "Member removed.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Could not remove member.");
                    }
                    break;

                case 2:  // Add Funds
                    id = JOptionPane.showInputDialog("Enter ID:");
                    double amount = Double.parseDouble(JOptionPane.showInputDialog("Enter amount to add:"));
                    Member member = club.members.get(id);  // Asumimos acceso directo para simplicidad
                    if (member != null) {
                        member.addFunds(amount);
                        JOptionPane.showMessageDialog(null, "Funds added.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Member not found.");
                    }
                    break;

                case 3:  // Add Authorized Person
                    id = JOptionPane.showInputDialog("Enter ID:");
                    member = club.members.get(id);
                    if (member != null) {
                        String person = JOptionPane.showInputDialog("Enter name of authorized person:");
                        member.addAuthorizedPerson(person);
                        JOptionPane.showMessageDialog(null, "Authorized person added.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Member not found.");
                    }
                    break;

                case 4:  // Pay Bill
                    id = JOptionPane.showInputDialog("Enter ID:");
                    member = club.members.get(id);
                    if (member != null) {
                        String bill = JOptionPane.showInputDialog("Enter bill concept to pay:");
                        if (member.payBill(bill)) {
                            JOptionPane.showMessageDialog(null, "Bill paid.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Could not pay bill.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Member not found.");
                    }
                    break;

                case 5:  // Show Member Info
                    id = JOptionPane.showInputDialog("Enter ID:");
                    club.showMemberInfo(id);
                    break;

                default:
                    break;
            }

        } while (choice != 6);  // Exit
    }
}
