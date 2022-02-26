package com.paycore.patika.credit_application_system.messaging.consumer;

import com.paycore.patika.credit_application_system.config.RabbitMQConfig;
import com.paycore.patika.credit_application_system.model.RabbitCustomerDTO;
import com.paycore.patika.credit_application_system.model.entity.Customer;
import com.paycore.patika.credit_application_system.model.mapper.RabbitCustomerMapper;
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

    private static final RabbitCustomerMapper RABBIT_CUSTOMER_MAPPER = Mappers.getMapper(RabbitCustomerMapper.class);


    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void consumeMessageFromQueue(RabbitCustomerDTO rabbitCustomerDTO) {
        Customer updatedCustomer = customerService.updateCustomer(RABBIT_CUSTOMER_MAPPER.toEntity(rabbitCustomerDTO));
        creditApplicationService.UpdateNotResultedApplication(updatedCustomer);
        System.out.println("Successfully received credit score and updated customer & credit application");
    }

}
