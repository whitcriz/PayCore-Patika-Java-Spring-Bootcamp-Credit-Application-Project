package com.paycore.patika.credit_score_service.service.impl;

import com.paycore.patika.credit_score_service.exception.NotFoundException;
import com.paycore.patika.credit_score_service.model.entity.Customer;
import com.paycore.patika.credit_score_service.repository.CustomerRepository;
import com.paycore.patika.credit_score_service.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private Integer CreditScoreCalculator() {
        Random random = new Random();
        return random.ints(1,(1501)).findFirst().getAsInt();
    }

    @Override
    public Customer addCustomer(Customer customer) {
        customer.setCreditScore(CreditScoreCalculator());
        return customerRepository.save(customer);
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
        customer.setCreditScore(CreditScoreCalculator());
        return customerRepository.save(customer);
    }


    @Override
    public boolean deleteCustomer(Integer id) {
        customerRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean deleteCustomer(String nationalIdentityNumber) {
        customerRepository.delete(getCustomer(nationalIdentityNumber));
        return true;
    }
}
