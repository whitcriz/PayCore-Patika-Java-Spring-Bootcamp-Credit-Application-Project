package com.paycore.patika.credit_application_system.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Data
@NoArgsConstructor
@Validated
@Entity
@Table(name = "customer")
public class Customer {

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

    @JsonIgnore
    @OneToMany(fetch = LAZY, mappedBy = "customer", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<CreditApplication> creditApplications;

}
