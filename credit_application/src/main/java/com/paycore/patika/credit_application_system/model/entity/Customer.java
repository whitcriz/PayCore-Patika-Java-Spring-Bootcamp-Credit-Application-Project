package com.paycore.patika.credit_application_system.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paycore.patika.credit_application_system.model.entity.CreditApplication;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "national_identity_number", updatable = false, nullable = false)
    @NotBlank(message = "national identity number can not be blank")
    @Size(min = 11, max = 11)
    @JsonFormat(pattern = "[1-9][0-9]{10}")
    private String nationalIdentityNumber;

    @NotBlank(message = "name can not be null")
    private String name;

    @NotBlank(message = "surname can not be null")
    private String surname;

    @NotBlank(message = "monthly income can not be null")
    @Column(name = "monthly_income")
    private BigDecimal monthlyIncome;

    private String gender;

    @Min(18)
    private  Integer age;

    @NotBlank(message = "phone can not be null")
    private String phone;

    @Email
    private String email;

    @Transient
    private Integer creditScore;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<CreditApplication> creditApplications;

}
