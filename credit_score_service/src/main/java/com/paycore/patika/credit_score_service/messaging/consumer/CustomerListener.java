package com.paycore.patika.credit_score_service.messaging.consumer;

import com.paycore.patika.credit_score_service.config.RabbitMQConfig;
import com.paycore.patika.credit_score_service.messaging.producer.CreditScoreProducer;
import com.paycore.patika.credit_score_service.model.CustomerDTO;
import com.paycore.patika.credit_score_service.model.entity.Customer;
import com.paycore.patika.credit_score_service.model.mapper.CustomerMapper;
import com.paycore.patika.credit_score_service.repository.CustomerRepository;
import com.paycore.patika.credit_score_service.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerListener {

    private final CustomerService customerService;

    private final CustomerRepository customerRepository;


    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void consumeMessageFromQueue(Customer customer) {

        Customer creditCustomer = customerRepository.findAll().stream()
                .filter(c -> c.getNationalIdentityNumber().matches(customer.getNationalIdentityNumber()))
                        .findAny()
                .orElse(customerService.addCustomer(customer));

        customerService.updateCustomer(creditCustomer);

    }
}
