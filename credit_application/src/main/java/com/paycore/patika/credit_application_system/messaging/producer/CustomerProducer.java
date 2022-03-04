package com.paycore.patika.credit_application_system.messaging.producer;

import com.paycore.patika.credit_application_system.config.RabbitMQConfig;
import com.paycore.patika.credit_application_system.model.RabbitCustomerDTO;
import com.paycore.patika.credit_application_system.model.entity.Customer;
import com.paycore.patika.credit_application_system.model.mapper.RabbitCustomerMapper;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/messaging/publish")
public class CustomerProducer {

    private final RabbitTemplate template;

    private static final RabbitCustomerMapper RABBIT_CUSTOMER_MAPPER = Mappers.getMapper(RabbitCustomerMapper.class);


    @PostMapping("/customer")
    public String publishCustomer(Customer customer) {
        template.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, RABBIT_CUSTOMER_MAPPER.toDto(customer));
        return "Created a credit application successfully ";
    }
}
