package com.paycore.patika.credit_application_system.model.mapper;

import com.paycore.patika.credit_application_system.model.ApplyCreditDTO;
import com.paycore.patika.credit_application_system.model.entity.CreditApplication;

public class ApplyCreditMapper {

    public static ApplyCreditDTO toDto(CreditApplication creditApplication) {
        ApplyCreditDTO applyCreditDTO = new ApplyCreditDTO();
        applyCreditDTO.setCustomer(creditApplication.getCustomer());
        return applyCreditDTO;
    }

    public static CreditApplication toEntity(ApplyCreditDTO applyCreditDTO) {
        CreditApplication creditApplication = new CreditApplication();
        creditApplication.setCustomer(applyCreditDTO.getCustomer());
        return creditApplication;
    }
}
