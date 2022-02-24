package com.paycore.patika.credit_application_system.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paycore.patika.credit_application_system.exception.InvalidRequestException;
import com.paycore.patika.credit_application_system.model.entity.Customer;
import com.paycore.patika.credit_application_system.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/all")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping(value = "/{nationalIdentityNumber}")
    public Customer getCustomer(@PathVariable @Size(min = 11, max = 11) @JsonFormat(pattern = "[1-9][0-9]{10}") String nationalIdentityNumber) {
        return customerService.getCustomer(nationalIdentityNumber);
    }

    @PostMapping("/add")
    public boolean addCustomer(@Valid @RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    @PutMapping(value = "/update")
    public Customer updateCustomer(@Valid @RequestBody Customer customer) {
        if(customer.getNationalIdentityNumber() == null) {
            throw new InvalidRequestException("Customer can not be null for update!");
        }
        return customerService.updateCustomer(customer);
    }

    @DeleteMapping(value = "/delete")
    public boolean deleteCustomer(@RequestParam @Min(1) Integer id) {
        return customerService.deleteCustomer(id);
    }

}
