package com.paycore.patika.credit_application_system.messaging.producer;

import com.paycore.patika.credit_application_system.config.RabbitMQConfig;
import com.paycore.patika.credit_application_system.exception.NotFoundException;
import com.paycore.patika.credit_application_system.model.entity.Customer;
import com.paycore.patika.credit_application_system.model.mapper.CustomerMapper;
import com.paycore.patika.credit_application_system.model.mapper.CustomerRabbitMapper;
import com.paycore.patika.credit_application_system.repository.CustomerRepository;
import com.paycore.patika.credit_application_system.service.CreditApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/messaging/publish")
public class CustomerProducer {


    @Autowired
    private RabbitTemplate template;

    @Autowired
    private CreditApplicationService creditApplicationService;

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/customer/{nationalIdentityNumber}")
    public String publishCustomer(@PathVariable String nationalIdentityNumber) {
        Optional<Customer> customer = customerRepository.findByNationalIdentityNumber(nationalIdentityNumber);
        template.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, CustomerRabbitMapper.toDtoRabbit(customer.orElseThrow(() -> new NotFoundException("Customer"))));
        return "Success";
    }
}
