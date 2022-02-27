package com.paycore.patika.credit_application_system.model.entity;

public enum CreditResult {

    NOT_RESULTED(0),
    REJECTED(500),
    APPROVED(1000);

    private Integer creditScoreLimit;


    CreditResult(Integer creditScoreLimit) {
        this.creditScoreLimit = creditScoreLimit;
    }

    public Integer getCreditScoreLimit() {
        return creditScoreLimit;
    }

    public void setCreditScoreLimit(Integer creditScoreLimit) {
        this.creditScoreLimit = creditScoreLimit;
    }



}
