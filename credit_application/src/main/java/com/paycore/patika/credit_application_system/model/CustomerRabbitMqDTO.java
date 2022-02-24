package com.paycore.patika.credit_application_system.model;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class CustomerRabbitMqDTO {

    private String nationalIdentityNumber;
    private String name;
    private String surname;
    private BigDecimal monthlyIncome;
    private Integer creditScore;

}
