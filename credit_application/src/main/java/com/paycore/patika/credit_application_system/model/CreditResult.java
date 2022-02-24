package com.paycore.patika.credit_application_system.model;

public enum CreditResult {

    REJECT(500,0.00,"REJECTED"),
    APPROVE(1000,5000.00,"APPROVED");

    private Integer creditScoreLimit;
    private Double incomeLimit;
    private String result;

    CreditResult(Integer creditScoreLimit, Double incomeLimit,String result) {
        this.result = result;
        this.creditScoreLimit = creditScoreLimit;
    }

    public Integer getCreditScoreLimit() {
        return creditScoreLimit;
    }

    public void setCreditScoreLimit(Integer creditScoreLimit) {
        this.creditScoreLimit = creditScoreLimit;
    }

    public Double getIncomeLimit() {
        return incomeLimit;
    }

    public void setIncomeLimit(Double incomeLimit) {
        this.incomeLimit = incomeLimit;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
