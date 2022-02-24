package com.paycore.patika.credit_application_system.model.mapper;

import com.paycore.patika.credit_application_system.model.CreditApplicationResultedDTO;
import com.paycore.patika.credit_application_system.model.entity.CreditApplication;
import org.mapstruct.Mapper;

@Mapper
public interface CreditApplicationResultedMapper {
    CreditApplicationResultedDTO toDto(CreditApplication entity);
    CreditApplication toEntity(CreditApplicationResultedDTO dto);
}
