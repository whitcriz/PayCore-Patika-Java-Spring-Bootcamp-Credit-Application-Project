package com.paycore.patika.credit_application_system.model;

import lombok.Data;

@Data
public class CustomerDTO {

    private String nationalIdentityNumber;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private Double monthlyIncome;
    private String gender;
    private Integer age;
}
