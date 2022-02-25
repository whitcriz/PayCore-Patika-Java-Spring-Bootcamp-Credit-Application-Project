package com.paycore.patika.credit_score_service.messaging.consumer;

import com.paycore.patika.credit_score_service.config.RabbitMQConfig;
import com.paycore.patika.credit_score_service.messaging.producer.CreditScoreProducer;
import com.paycore.patika.credit_score_service.model.CreditApplication;
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
public class CreditApplicationListener {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CreditScoreProducer creditScoreProducer;

    private final CustomerMapper CUSTOMER_MAPPER = Mappers.getMapper(CustomerMapper.class);

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void consumeMessageFromQueue(CreditApplication creditApplication) {
        Optional<Customer> customer = customerRepository.findByNationalIdentityNumber(creditApplication.getCustomer().getNationalIdentityNumber());
        Customer updatedCustomer =customerService.updateCustomer(customer.orElse(customerService.addCustomer(creditApplication.getCustomer())));
        creditApplication.setCreditScore(updatedCustomer.getCreditScore());
        creditScoreProducer.publishCreditScore(creditApplication);
    }
}
