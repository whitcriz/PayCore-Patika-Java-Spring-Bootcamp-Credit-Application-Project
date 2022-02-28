package com.paycore.patika.credit_application_system.model;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Transient;
import javax.validation.constraints.*;

@Data
@Validated
public class CustomerDTO {

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

}
