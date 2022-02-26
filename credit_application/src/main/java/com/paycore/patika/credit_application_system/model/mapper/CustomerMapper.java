package com.paycore.patika.credit_application_system.model.mapper;

import com.paycore.patika.credit_application_system.model.CustomerDTO;
import com.paycore.patika.credit_application_system.model.entity.Customer;


public class CustomerMapper {

    public static CustomerDTO toDto(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setNationalIdentityNumber(customer.getNationalIdentityNumber());
        customerDTO.setName(customer.getName());
        customerDTO.setSurname(customer.getSurname());
        customerDTO.setPhone(customer.getPhone());
        customerDTO.setMonthlyIncome(customer.getMonthlyIncome());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setAge(customer.getAge());
        customerDTO.setGender(customer.getGender());
        return customerDTO;
    }

    public static Customer toEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setNationalIdentityNumber(customerDTO.getNationalIdentityNumber());
        customer.setName(customerDTO.getName());
        customer.setSurname(customerDTO.getSurname());
        customer.setPhone(customerDTO.getPhone());
        customer.setMonthlyIncome(customerDTO.getMonthlyIncome());
        customer.setEmail(customerDTO.getEmail());
        customer.setAge(customerDTO.getAge());
        customer.setGender(customerDTO.getGender());
        return customer;
    }
}
