package com.paycore.patika.credit_application_system.model.mapper;

import com.paycore.patika.credit_application_system.model.entity.Customer;
import com.paycore.patika.credit_application_system.model.CustomerRabbitMqDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
    CustomerRabbitMqDTO toDto(Customer entity);

    Customer toEntity(CustomerRabbitMqDTO dto);
}
