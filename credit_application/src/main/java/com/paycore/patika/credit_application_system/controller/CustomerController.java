package com.paycore.patika.credit_application_system.controller;

import com.paycore.patika.credit_application_system.exception.InvalidRequestException;
import com.paycore.patika.credit_application_system.exception.NotFoundException;
import com.paycore.patika.credit_application_system.model.CustomerDTO;
import com.paycore.patika.credit_application_system.model.entity.Customer;
import com.paycore.patika.credit_application_system.model.mapper.CustomerMapper;
import com.paycore.patika.credit_application_system.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {


    private final CustomerService customerService;


    @GetMapping("/all")
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> allCustomers = customerService.getAllCustomers();
        return allCustomers.stream().map(CustomerMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping(value = "/{nationalIdentityNumber}")
    public CustomerDTO getCustomer(@PathVariable @Size @Pattern(regexp = "[1-9][0-9]{10}") String nationalIdentityNumber) {
        return CustomerMapper.toDto(customerService.getCustomer(nationalIdentityNumber));
    }

    @PostMapping("/add")
    public boolean addCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        return customerService.addCustomer(CustomerMapper.toEntity(customerDTO));
    }

    @PutMapping(value = "/update")
    public CustomerDTO updateCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        if(customerService.getCustomer(customerDTO.getNationalIdentityNumber())==null) {
            throw new NotFoundException("Customer could not found for update");
        }
        return CustomerMapper.toDto(customerService.updateCustomer(CustomerMapper.toEntity(customerDTO)));
    }

    @DeleteMapping(value = "/delete/{nationalIdentityNumber}")
    public boolean deleteCustomer(@PathVariable @Pattern(regexp = "[1-9][0-9]{10}") String nationalIdentityNumber) {
        return customerService.deleteCustomer(nationalIdentityNumber);
    }

}
