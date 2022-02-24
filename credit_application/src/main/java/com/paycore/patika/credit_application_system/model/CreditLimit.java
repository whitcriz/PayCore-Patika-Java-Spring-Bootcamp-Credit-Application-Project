package com.paycore.patika.credit_application_system.model;


public enum CreditLimit {

    LOWER(10000.00),
    HIGHER(20000.00),
    SPECIAL(0.00);

    private Double creditLimitAmount;

    CreditLimit(Double creditLimitAmount) {
        this.creditLimitAmount = creditLimitAmount;
    }

    public Double getCreditLimitAmount() {
        return creditLimitAmount;
    }

    public void setCreditLimitAmount(Double creditLimitAmount) {
        this.creditLimitAmount = creditLimitAmount;
    }
}
