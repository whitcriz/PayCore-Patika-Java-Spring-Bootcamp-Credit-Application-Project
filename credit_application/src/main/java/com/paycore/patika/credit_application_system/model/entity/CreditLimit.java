package com.paycore.patika.credit_application_system.model.entity;


public enum CreditLimit {

    LOWER(10000.00,5000.00),
    HIGHER(20000.00,5000.00),
    SPECIAL(0.00,0.00);

    private Double creditLimitAmount;
    private Double incomeLimit;


    CreditLimit(Double creditLimitAmount, Double incomeLimit) {
        this.incomeLimit = incomeLimit;
        this.creditLimitAmount = creditLimitAmount;
    }

    public Double getCreditLimitAmount() {
        return creditLimitAmount;
    }

    public void setCreditLimitAmount(Double creditLimitAmount) {
        this.creditLimitAmount = creditLimitAmount;
    }

    public Double getIncomeLimit() {
        return incomeLimit;
    }

    public void setIncomeLimit(Double incomeLimit) {
        this.incomeLimit = incomeLimit;
    }

}
