package com.paycore.patika.credit_application_system.model.mapper;

import com.paycore.patika.credit_application_system.model.CustomerRabbitMqDTO;
import com.paycore.patika.credit_application_system.model.entity.Customer;

public class CustomerRabbitMapper {

    public static CustomerRabbitMqDTO toDtoRabbit(Customer customer) {
        CustomerRabbitMqDTO customerRabbitMqDTO = new CustomerRabbitMqDTO();
        customerRabbitMqDTO.setName(customer.getName());
        customerRabbitMqDTO.setSurname(customer.getSurname());
        customerRabbitMqDTO.setMonthlyIncome(customer.getMonthlyIncome());
        customerRabbitMqDTO.setNationalIdentityNumber(customer.getNationalIdentityNumber());
        customerRabbitMqDTO.setCreditScore(customer.getCreditScore());
        customerRabbitMqDTO.setCreditApplication(customer.getCreditApplication());
        return customerRabbitMqDTO;
    }

    public static Customer toEntityRabbit(CustomerRabbitMqDTO customerRabbitMqDTO) {
        Customer customer = new Customer();
        customer.setName(customerRabbitMqDTO.getName());
        customer.setSurname(customerRabbitMqDTO.getSurname());
        customer.setMonthlyIncome(customerRabbitMqDTO.getMonthlyIncome());
        customer.setNationalIdentityNumber(customerRabbitMqDTO.getNationalIdentityNumber());
        customer.setCreditScore(customerRabbitMqDTO.getCreditScore());
        customer.setCreditApplication(customerRabbitMqDTO.getCreditApplication());
        return customer;
    }
}



