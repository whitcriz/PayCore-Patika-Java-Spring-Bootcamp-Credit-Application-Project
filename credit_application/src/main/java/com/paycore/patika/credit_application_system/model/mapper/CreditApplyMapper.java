package com.paycore.patika.credit_application_system.model.mapper;

import com.paycore.patika.credit_application_system.model.CreditApplyDTO;
import com.paycore.patika.credit_application_system.model.entity.CreditApplication;

public class CreditApplyMapper {

    public static CreditApplyDTO toDto(CreditApplication creditApplication) {
        CreditApplyDTO creditApplyingDTO = new CreditApplyDTO();
        creditApplyingDTO.setCustomer(creditApplication.getCustomer());
        return creditApplyingDTO;
    }

    public static CreditApplication toEntity(CreditApplyDTO creditApplyingDTO) {
        CreditApplication creditApplication = new CreditApplication();
        creditApplication.setCustomer(creditApplyingDTO.getCustomer());
        return creditApplication;
    }
}

