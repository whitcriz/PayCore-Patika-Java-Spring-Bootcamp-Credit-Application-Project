package com.paycore.patika.credit_application_system.model.mapper;

import com.paycore.patika.credit_application_system.model.RabbitCustomerDTO;
import com.paycore.patika.credit_application_system.model.entity.Customer;
import org.mapstruct.Mapper;

@Mapper
public interface RabbitCustomerMapper {

    RabbitCustomerDTO toDto(Customer entity);

    Customer toEntity(RabbitCustomerDTO dto);
}
