/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

/**
 *
 * @author ssuaz
 */
public class CustomsExceptions {

    class MemberNotFoundException extends Exception {

        public MemberNotFoundException(String message) {
            super(message);
        }
    }

    class VIPMemberDeletionException extends Exception {

        public VIPMemberDeletionException(String message) {
            super(message);
        }
    }

    class UnpaidBillsException extends Exception {

        public UnpaidBillsException(String message) {
            super(message);
        }
    }

    class MultipleAuthorizedPersonsException extends Exception {

        public MultipleAuthorizedPersonsException(String message) {
            super(message);
        }

    }

    class ExceedsMaxFundsException extends Exception {

        public ExceedsMaxFundsException(String message) {
            super(message);
        }
    }

}
