
package com.mycompany.main;

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
