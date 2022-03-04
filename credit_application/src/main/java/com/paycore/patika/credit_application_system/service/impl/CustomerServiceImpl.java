package com.paycore.patika.credit_application_system.service.impl;

import com.paycore.patika.credit_application_system.exception.NotFoundException;
import com.paycore.patika.credit_application_system.model.entity.Customer;
import com.paycore.patika.credit_application_system.repository.CustomerRepository;
import com.paycore.patika.credit_application_system.service.CustomerService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public boolean addCustomer(Customer customer) {
        customerRepository.save(customer);
        return true;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomer(String nationalIdentityNumber) {
        Optional<Customer> customer = customerRepository.findByNationalIdentityNumber(nationalIdentityNumber);
        return customer.orElseThrow(() -> new NotFoundException("Customer"));
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomerCreditScore(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public boolean deleteCustomer(String nationalIdentityNumber) {
        customerRepository.delete(getCustomer(nationalIdentityNumber));
        return true;
    }
}

