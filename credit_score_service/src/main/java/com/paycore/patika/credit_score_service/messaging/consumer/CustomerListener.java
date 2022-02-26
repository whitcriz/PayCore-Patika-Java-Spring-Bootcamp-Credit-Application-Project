package com.paycore.patika.credit_score_service.messaging.consumer;

import com.paycore.patika.credit_score_service.config.RabbitMQConfig;
import com.paycore.patika.credit_score_service.messaging.producer.CreditScoreProducer;
import com.paycore.patika.credit_score_service.model.CreditApplication;
import com.paycore.patika.credit_score_service.model.CustomerDTO;
import com.paycore.patika.credit_score_service.model.entity.Customer;
import com.paycore.patika.credit_score_service.model.mapper.CustomerMapper;
import com.paycore.patika.credit_score_service.repository.CustomerRepository;
import com.paycore.patika.credit_score_service.service.CustomerService;
import org.mapstruct.factory.Mappers;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerListener {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CreditScoreProducer creditScoreProducer;

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void consumeMessageFromQueue(Customer customer) {
        Optional<Customer> creditCustomer = customerRepository.findByNationalIdentityNumber(customer.getNationalIdentityNumber());
        Customer updatedCustomer =customerService.updateCustomer(creditCustomer.orElse(customerService.addCustomer(customer)));
        creditScoreProducer.publishCreditScore(updatedCustomer);
    }
}
