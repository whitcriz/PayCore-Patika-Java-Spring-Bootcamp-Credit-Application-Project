package com.paycore.patika.credit_score_service.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Data
public class CustomerDTO {

    @Pattern(regexp = "[1-9]\\d{10}")
    private String nationalIdentityNumber;

    private String name;
    private String surname;
    private Double monthlyIncome;

    @Min(1)
    private Integer creditScore;
}
