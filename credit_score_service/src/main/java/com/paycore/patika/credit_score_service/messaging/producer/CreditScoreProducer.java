package com.paycore.patika.credit_score_service.messaging.producer;

import com.paycore.patika.credit_score_service.config.RabbitMQConfig;
import com.paycore.patika.credit_score_service.model.entity.Customer;
import com.paycore.patika.credit_score_service.model.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/messaging/publish")
public class CreditScoreProducer {

    private final RabbitTemplate template;
    private static final CustomerMapper CUSTOMER_MAPPER = Mappers.getMapper(CustomerMapper.class);

    @PostMapping("/credit-score")
    public void publishCreditScore(Customer customer) {
        template.convertAndSend(RabbitMQConfig.EXCHANGE_SCORE, RabbitMQConfig.ROUTING_KEY_SCORE, CUSTOMER_MAPPER.toDto(customer));
        System.out.println("Success");
    }

}