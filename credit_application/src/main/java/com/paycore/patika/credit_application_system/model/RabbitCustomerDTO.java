package com.paycore.patika.credit_application_system.model;

import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;

@Data
public class RabbitCustomerDTO implements Serializable {

    @NotNull(message = "national identity number can not be blank")
    @Pattern(regexp = "[1-9][0-9]{10}")
    private String nationalIdentityNumber;

    @NotBlank(message = "name can not be null")
    private String name;

    @NotBlank(message = "surname can not be null")
    private String surname;

    @NotBlank(message = "phone can not be null")
    private String phone;

    @Email
    private String email;

    @NotNull(message = "monthly income can not be null")
    @Min(1)
    private Double monthlyIncome;

    private String gender;

    @Min(18)
    private Integer age;

    private Integer creditScore;
}
