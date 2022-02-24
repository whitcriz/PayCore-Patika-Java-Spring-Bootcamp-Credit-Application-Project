package com.paycore.patika.credit_application_system.model;

import com.paycore.patika.credit_application_system.model.entity.Customer;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Data
public class CreditApplicationResultedDTO {

    private LocalDate creditApplicationDate;

    @Enumerated
    private Resulted resulted;

    @Enumerated(EnumType.STRING)
    private CreditResult creditResult;

    @Enumerated(EnumType.ORDINAL)
    private CreditLimit creditLimit;

    private Customer customer;

}
