package com.paycore.patika.credit_application_system.exception;

public class InvalidCreditApplyException extends RuntimeException {

    public InvalidCreditApplyException(String message) {

        super("You have an active credit application "+ message);

    }
}
