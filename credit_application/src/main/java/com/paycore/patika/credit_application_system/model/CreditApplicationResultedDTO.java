package com.paycore.patika.credit_application_system.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paycore.patika.credit_application_system.model.entity.ApplicationStatus;
import com.paycore.patika.credit_application_system.model.entity.CreditResult;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Data
public class CreditApplicationResultedDTO {

    @CreationTimestamp
    @JsonFormat( pattern = "dd-MM-yyyy" )
    private LocalDate creditApplicationDate;

    @Enumerated(EnumType.STRING)
    private CreditResult creditResult;

    private Double creditLimit;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;


}
