package com.paycore.patika.credit_application_system.model;

import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;

@Data
public class RabbitCustomerDTO implements Serializable {

    private String nationalIdentityNumber;

    private String name;

    private String surname;

    private String phone;

    private String email;

    private Double monthlyIncome;

    private String gender;

    private Integer age;

    private Integer creditScore;
}
