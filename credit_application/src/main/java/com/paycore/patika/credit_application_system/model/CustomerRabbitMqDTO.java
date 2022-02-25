package com.paycore.patika.credit_application_system.model;

import com.paycore.patika.credit_application_system.model.entity.CreditApplication;
import lombok.Data;

@Data
public class CustomerRabbitMqDTO {

    private String nationalIdentityNumber;
    private String name;
    private String surname;
    private Double monthlyIncome;
    private Integer creditScore;
    private CreditApplication creditApplication;
}