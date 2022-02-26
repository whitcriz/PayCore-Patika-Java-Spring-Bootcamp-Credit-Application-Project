package com.paycore.patika.credit_score_service.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
public class Customer implements Serializable {

    @Id
    @Column(name = "national_identity_number", length = 11,updatable = false, nullable = false)
    @NotBlank(message = "national identity number can not be null")
    @Pattern(regexp = "[1-9]\\d{10}")
    private String nationalIdentityNumber;

    @NotBlank(message = "name can not be null")
    private String name;

    @NotBlank(message = "surname can not be null")
    private String surname;

    @NotBlank(message = "monthly income can not be null")
    @Min(1)
    @Column(name = "monthly_income")
    private Double monthlyIncome;

    private String gender;

    @Min(18)
    private  Integer age;

    @NotBlank(message = "phone can not be null")
    private String phone;

    @Email
    private String email;

    @Column(name = "credit_score")
    private Integer creditScore;
}
