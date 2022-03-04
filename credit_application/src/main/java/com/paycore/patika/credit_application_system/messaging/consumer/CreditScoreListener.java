package com.paycore.patika.credit_application_system.messaging.consumer;

import com.paycore.patika.credit_application_system.config.RabbitMQConfig;
import com.paycore.patika.credit_application_system.model.RabbitCustomerDTO;

import com.paycore.patika.credit_application_system.model.entity.Customer;
import com.paycore.patika.credit_application_system.model.mapper.RabbitCustomerMapper;
import com.paycore.patika.credit_application_system.service.CreditApplicationService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreditScoreListener {

    private final CreditApplicationService creditApplicationService;

    private static final RabbitCustomerMapper RABBIT_CUSTOMER_MAPPER = Mappers.getMapper(RabbitCustomerMapper.class);


    @RabbitListener(queues = RabbitMQConfig.QUEUE_SCORE)
    public void consumeMessageFromQueue(RabbitCustomerDTO rabbitCustomerDTO) {
        Customer resultedCustomer = RABBIT_CUSTOMER_MAPPER.toEntity(rabbitCustomerDTO);
        creditApplicationService.UpdateNotResultedApplication(resultedCustomer);
        System.out.println("Successfully received credit score and resulted credit application");
    }

}
