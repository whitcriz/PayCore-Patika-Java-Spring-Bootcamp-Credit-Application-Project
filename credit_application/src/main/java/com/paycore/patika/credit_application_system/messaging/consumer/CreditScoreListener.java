package com.paycore.patika.credit_application_system.messaging.consumer;

import com.paycore.patika.credit_application_system.config.RabbitMQConfig;
import com.paycore.patika.credit_application_system.model.CustomerRabbitMqDTO;
import com.paycore.patika.credit_application_system.model.entity.CreditApplication;
import com.paycore.patika.credit_application_system.model.entity.Customer;
import com.paycore.patika.credit_application_system.model.mapper.CustomerRabbitMapper;
import com.paycore.patika.credit_application_system.service.CreditApplicationService;
import com.paycore.patika.credit_application_system.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CreditScoreListener {

    @Autowired
    private CreditApplicationService creditApplicationService;

    @Autowired
    private CustomerService customerService;


    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void consumeMessageFromQueue(CustomerRabbitMqDTO customerRabbitMqDTO) {
        Customer customer = CustomerRabbitMapper.toEntityRabbit(customerRabbitMqDTO);
        CreditApplication creditApplication = customerRabbitMqDTO.getCreditApplication();
        customerService.updateCustomer(customer);
        creditApplicationService.updateCreditApplication(creditApplication);
    }

}
