package com.paycore.patika.credit_score_service.service.impl;

import com.paycore.patika.credit_score_service.exception.NotFoundException;
import com.paycore.patika.credit_score_service.messaging.producer.CreditScoreProducer;
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

    private final CreditScoreProducer creditScoreProducer;

    private Integer CreditScoreCalculator() {
        Random random = new Random();
        return random.nextInt(1850)+200;
    }

    @Override
    public Customer addCustomer(Customer customer) {
        String identityLast = customer.getNationalIdentityNumber().substring(8);
        customer.setCreditScore(CreditScoreCalculator() + Integer.parseInt(identityLast));
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
    public void updateCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void updateCreditScore(Customer customer) {
        String identityFirst = customer.getNationalIdentityNumber().substring(0,3);
        customer.setCreditScore(CreditScoreCalculator() + Integer.parseInt(identityFirst));
        Customer updatedCustomer = customerRepository.save(customer);
        creditScoreProducer.publishCreditScore(updatedCustomer);
    }

}
