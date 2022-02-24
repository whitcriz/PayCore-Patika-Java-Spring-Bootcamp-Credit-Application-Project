package com.paycore.patika.credit_application_system.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paycore.patika.credit_application_system.exception.InvalidRequestException;
import com.paycore.patika.credit_application_system.model.CustomerDTO;
import com.paycore.patika.credit_application_system.model.entity.Customer;
import com.paycore.patika.credit_application_system.model.mapper.CustomerMapper;
import com.paycore.patika.credit_application_system.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    private static final CustomerMapper CUSTOMER_MAPPER = Mappers.getMapper(CustomerMapper.class);

    @GetMapping("/all")
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> allCustomers = customerService.getAllCustomers();
        return allCustomers.stream().map(CUSTOMER_MAPPER::toDto).collect(Collectors.toList());
    }

    @GetMapping(value = "/{nationalIdentityNumber}")
    public CustomerDTO getCustomer(@PathVariable @Size(min = 11, max = 11) @JsonFormat(pattern = "[1-9][0-9]{10}") String nationalIdentityNumber) {
        Customer customer = customerService.getCustomer(nationalIdentityNumber);
        return CUSTOMER_MAPPER.toDto(customer);
    }

    @PostMapping("/add")
    public boolean addCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        return customerService.addCustomer(CUSTOMER_MAPPER.toEntity(customerDTO));
    }

    @PutMapping(value = "/update")
    public CustomerDTO updateCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        String nationalIdentityNumber = customerDTO.getNationalIdentityNumber();
        if(nationalIdentityNumber == null) {
            throw new InvalidRequestException("Customer National Identity Number can not be null for update!");
        }
        Customer customer = customerService.getCustomer(nationalIdentityNumber);
        return CUSTOMER_MAPPER.toDto(customerService.updateCustomer(customer));
    }

    @DeleteMapping(value = "/delete/{nationalIdentityNumber}")
    public boolean deleteCustomer(@PathVariable @Size(min = 11, max = 11) @JsonFormat(pattern = "[1-9][0-9]{10}") String nationalIdentityNumber) {
        return customerService.deleteCustomer(nationalIdentityNumber);
    }

}
