/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.clubwork;

import java.util.Scanner;

/**
 *
 * @author ssuaz
 */


public class ClubWork {
    public static void main(String[] args) {
        Club club = new Club();
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n--- Social Club Menu ---");
            System.out.println("1. Enroll Member");
            System.out.println("2. Register Authorized Person");
            System.out.println("3. Pay Invoice");
            System.out.println("4. Register Consumption");
            System.out.println("5. Add Funds");
            System.out.println("6. Remove Member");
            System.out.println("7. Show Members");
            System.out.println("8. Show Authorized Persons and Their Members");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    // Enroll a new member
                    System.out.print("Enter the member's ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    
                    System.out.print("Enter the member's name: ");
                    String name = scanner.next();
                    scanner.nextLine();
                    
                    System.out.print("Enter the subscription type (VIP or Regular): ");
                    String subscriptionType = scanner.next();
                    scanner.nextLine();
                    
                    try {
                        Member newMember = new Member(id, name, subscriptionType);
                        club.addMember(newMember);
                        System.out.println("Member successfully enrolled.");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    // Register an authorized person for a member
                    System.out.print("Enter the member's ID: ");
                    id = scanner.nextInt();
                    Member member = club.findMember(id);
                    if (member == null) {
                        System.out.println("No member found with that ID.");
                    } else {
                        System.out.print("Enter the authorized person's ID: ");
                        int authorizedId = scanner.nextInt();
                        scanner.nextLine();
                        
                        System.out.print("Enter the authorized person's name: ");
                        String authorizedName = scanner.next();
                        scanner.nextLine();
                        
                        try {
                            AuthorizedAffiliate newAuthorized = new AuthorizedAffiliate(authorizedId, authorizedName);
                            member.addAuthorizedPerson(newAuthorized);
                            System.out.println("Authorized person successfully registered.");
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                    break;

                case 3:
                    // Pay an invoice
                    System.out.print("Enter the member's ID: ");
                    id = scanner.nextInt();
                    member = club.findMember(id);
                    if (member == null) {
                        System.out.println("No member found with that ID.");
                    } else if (member.getPendingInvoices().isEmpty()) {
                        System.out.println("No pending invoices for this member.");
                    } else {
                        System.out.println("Pending invoices:");
                        for (int i = 0; i < member.getPendingInvoices().size(); i++) {
                            Invoice invoice = member.getPendingInvoices().get(i);
                            System.out.println((i + 1) + ". Concept: " + invoice.getConcept() + ", Amount: " + invoice.getAmount());
                        }
                        System.out.print("Select the number of the invoice you want to pay: ");
                        int invoiceNumber = scanner.nextInt();
                        try {
                            member.payInvoice(invoiceNumber - 1);
                            System.out.println("Invoice successfully paid.");
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                    break;

                case 4:
                    // Register a consumption
                    System.out.print("Enter the member's ID: ");
                    id = scanner.nextInt();
                    member = club.findMember(id);
                    if (member == null) {
                        System.out.println("No member found with that ID.");
                    } else {
                        System.out.print("Enter the consumption concept: ");
                        String concept = scanner.next();
                        System.out.print("Enter the consumption amount: ");
                        double amount = scanner.nextDouble();
                        try {
                            member.generateInvoice(concept, amount);
                            System.out.println("Consumption successfully registered.");
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                    break;

                case 5:
                    // Add funds to a member's account
                    System.out.print("Enter the member's ID: ");
                    id = scanner.nextInt();
                    member = club.findMember(id);
                    if (member == null) {
                        System.out.println("No member found with that ID.");
                    } else {
                        System.out.print("Enter the amount to add: ");
                        double amountToAdd = scanner.nextDouble();
                        try {
                            member.addFunds(amountToAdd);
                            System.out.println("Funds successfully added.");
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                    break;

                case 6:
                    // Remove a member
                    System.out.print("Enter the ID of the member you want to remove: ");
                    id = scanner.nextInt();
                    try {
                        club.removeMember(id);
                        System.out.println("Member successfully removed.");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 7:
                    // Show list of members
                    club.showMembers();
                    break;

                case 8:
                    // Show authorized persons and their affiliated members
                    club.showAuthorizedPersons();
                    break;

                case 0:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        } while (option != 0);

        scanner.close();
    }
}

