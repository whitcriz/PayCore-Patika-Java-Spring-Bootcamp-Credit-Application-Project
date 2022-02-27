package com.paycore.patika.credit_application_system.messaging.consumer;

import com.paycore.patika.credit_application_system.config.RabbitMQConfig;
import com.paycore.patika.credit_application_system.model.RabbitCustomerDTO;

import com.paycore.patika.credit_application_system.service.CreditApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CreditScoreListener {

    private final CreditApplicationService creditApplicationService;


    @RabbitListener(queues = RabbitMQConfig.QUEUE_SCORE)
    public void consumeMessageFromQueue(RabbitCustomerDTO rabbitCustomerDTO) {
        creditApplicationService.UpdateNotResultedApplication(
                rabbitCustomerDTO.getCreditScore(),rabbitCustomerDTO.getNationalIdentityNumber());
        System.out.println("Successfully received credit score and resulted credit application");
    }

}
