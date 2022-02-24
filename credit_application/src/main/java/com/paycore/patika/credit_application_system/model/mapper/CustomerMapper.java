package com.paycore.patika.credit_application_system.model.mapper;

import com.paycore.patika.credit_application_system.model.CustomerDTO;
import com.paycore.patika.credit_application_system.model.entity.Customer;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    CustomerDTO toDto(Customer entity);
    Customer toEntity(CustomerDTO dto);
}
