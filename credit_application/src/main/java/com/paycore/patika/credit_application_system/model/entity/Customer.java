package com.paycore.patika.credit_application_system.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Entity
@Table(name = "customer")
public class Customer implements Serializable {

    @Id
    @Column(name = "national_identity_number",length = 11,updatable = false, nullable = false)
    @NotBlank(message = "national identity number can not be blank")
    @Pattern(regexp = "[1-9][0-9]{10}")
    private String nationalIdentityNumber;

    @NotBlank(message = "name can not be null")
    private String name;

    @NotBlank(message = "surname can not be null")
    private String surname;

    @NotNull(message = "monthly income can not be null")
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

    @Transient
    private Integer creditScore;

    @Transient
    @OneToMany(fetch = LAZY, mappedBy = "customer", cascade = CascadeType.ALL,orphanRemoval = true)
    //@JoinColumn(name = "application_id",referencedColumnName = "application_id")
    private List<CreditApplication> creditApplications;


    public Customer(String nationalIdentityNumber, String name, String surname, Double monthlyIncome, String gender, Integer age, String phone, String email) {
        this.nationalIdentityNumber = nationalIdentityNumber;
        this.name = name;
        this.surname = surname;
        this.monthlyIncome = monthlyIncome;
        this.gender = gender;
        this.age = age;
        this.phone = phone;
        this.email = email;
    }
}
