package com.paycore.patika.credit_score_service.model.mapper;

import com.paycore.patika.credit_score_service.model.CustomerDTO;
import com.paycore.patika.credit_score_service.model.entity.Customer;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper
public interface CustomerMapper {

    CustomerDTO toDto(Customer entity);

    Customer toEntity(CustomerDTO dto);
}
